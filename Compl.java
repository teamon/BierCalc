public class Compl {
    public class ComplException extends Exception {
        public ComplException(String message){
            super(message);
        }
    }
    
    
    public int[] bits;
    public int base;
    
    public Compl(int base){
        this(base, new int[]{0});
    }
    
    public Compl(int base, String raw){
        this.base = base;
        this.bits = stringToBits(raw);
    }
    
    public Compl(int base, int[] bits){
        this.base = base;
        this.bits = bits;
    }
    
    public int toInt(){
        if(isNegative()) return -bitsToInt(bitsComplacement()); // negative number
        else return bitsToInt(bits);
    }
    
    public boolean isNegative(){
        return bits[bits.length-1] >= base/2;
    }
        
    public int[] bitsComplacement(){
        int i;
        int[] c = new int[bits.length];
        
        for(i=0; i<bits.length && bits[i]==0; i++){
            c[i] = bits[i];
        }
        
        c[i] = base - bits[i];
        i++;
        
        for(; i<bits.length; i++){
            c[i] = base - 1 - bits[i];
        }
        
        return c;        
    }
    
    public int bitAt(int index){
        if(index >= bits.length) {
            if(isNegative()) return base-1;
            else return 0;
        } else {
            return bits[index];
        }
    }
    
    public int bitLength(){
        return bits.length;
    }
    
    
    public String toString(){        
        StringBuffer s = new StringBuffer();
        for(int i=bits.length-1; i>=0; i--){
            s.append(bits[i]);
        }
        return s.toString();
    }
    
    public Compl add(Compl that) throws ComplException { 
        p(this.base);
        p(that.base);
        if(this.base != that.base) throw new ComplException("Invalid base");
               
        int size = Math.max(this.bitLength(), that.bitLength()) + 1;
        int sum[] = new int[size];
        int c = 0;
        
        for(int i=0; i<size; i++){
            int x = this.bitAt(i) + that.bitAt(i) + c;
            sum[i] = x % base;
            c = x / base;
        }
        
        return new Compl(base, sum);
    }
    
    
    // helper functions
    protected int[] stringToBits(String str){
        int len = str.length();
        // Convert to int and reverse
        int[] bits = new int[len];
        for(int i=0; i<len; i++){
            bits[i] = Integer.parseInt("" + str.charAt(len-i-1));
        }
        
        return bits;
    }
    
    protected int bitsToInt(int[] b){
        int a = 0;
        for(int i=0; i<b.length; i++){
            a += b[i] * Math.pow(base, i);
        }
        return a;
    }
    
    
    
    public static void p(int[] a){
        System.out.print("[");
        if(a.length > 0){
            System.out.print(a[0]);
            for(int i=1; i<a.length; i++){
                System.out.print("," + a[i]);
            }
        }
        System.out.println("]");
    }
    
    public static void p(Object o){
        System.out.println(o);
    }
    
    public static void p(String label, int[] a){
        System.out.print(label);
        p(a);
    }
    
    public static void p(String label, Object a){
        System.out.print(label);
        p(a);
    }
    
    public static void main(String[] args) {
        try {
            int base = Integer.parseInt(args[0]);
            
            Compl a = new Compl(base, args[1]);
            p("String: ", a.toString());
            p("Int:    ", a.toInt());

            Compl b = new Compl(base, args[2]);
            p("String: ", b.toString());
            p("Int:    ", b.toInt());
        
            Compl c = a.add(b);
        
            p("String: ", c.toString());
            p("Int:    ", c.toInt());
        } catch (ComplException e){
            p("Error: " + e.getMessage());
        }
    }
}