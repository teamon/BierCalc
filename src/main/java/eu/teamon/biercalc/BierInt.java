package eu.teamon.biercalc;

class BierIntException extends Exception {
    public BierIntException(String message){
        super(message);
    }
}

public class BierInt {
    public int[] bits;
    public int base;

    public BierInt(int base){
        this(base, new int[]{0});
    }

    public BierInt(int base, String raw){
        this.base = base;
        // this.dot = raw.indexOf('.');
        this.bits = stringToBits(raw);
    }

    public BierInt(int base, int[] bits){
        this.base = base;
        this.bits = bits;
    }

    public int toInt(){
        if(isNegative()) return -bitsToInt(bitsComplacement()); // negative number
        else return bitsToInt(bits);
    }

    public boolean isNegative(){
        if(bits.length != 0) return bits[bits.length-1] >= base/2;
        else return false;
    }

    public BierInt Complacement(){
        return new BierInt(base, bitsComplacement());
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
            if(bits[i] > 9) s.append((char)(bits[i]+55));
            else s.append(bits[i]);
        }
        return s.toString();
    }

    public BierInt add(BierInt that) throws BierIntException {
        if(base != that.base) throw new BierIntException("Invalid base");

        int size = Math.max(bitLength(), that.bitLength()) + 1;
        int sum[] = new int[size];
        int c = 0;

        for(int i=0; i<size; i++){
            int x = bitAt(i) + that.bitAt(i) + c;
            sum[i] = x % base;
            c = x / base;
        }

        return new BierInt(base, sum);
    }

    public BierInt subtract(BierInt that) throws BierIntException {
        return this.add(that.Complacement());
    }

    // helper functions
    protected int[] stringToBits(String str){
        int len = str.length();
        // Convert to int and reverse
        int[] bits = new int[len];
        for(int i=0; i<len; i++){
            char c = str.charAt(len-i-1);
            try {
                bits[i] = Integer.parseInt("" + c);
            } catch (NumberFormatException e){
                bits[i] = Integer.decode("0x"+c);
            }
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
    
    public static void display(char operator, BierInt a, BierInt b, BierInt c){
        int size = Math.max(Math.max(a.toString().length(), b.toString().length()), c.toString().length()) + 2;
                    
        System.out.println(String.format("%" + size + "s = %" + size + "d", a.toString(), a.toInt()));
        System.out.println(String.format(operator + "%" + (size-1) + "s = %" + size + "d", b.toString(), b.toInt()));
        
        for(int i=0, n = 2*size+3; i<n; i++) System.out.print("-");
        System.out.println();

        System.out.println(String.format("%" + size + "s = %" + size + "d", c.toString(), c.toInt()));
    }
    
    
    public static void main(String[] args) {
        try {
            int base = Integer.parseInt(args[0]);
            BierInt a = new BierInt(base, args[1]);
            BierInt b = new BierInt(base, args[3]);
            char operator = args[2].charAt(0);
            
            switch(operator){
                case '+':
                    display(operator, a, b, a.add(b));
                    break;
                    
                case '-':
                    display(operator, a, b, a.subtract(b));
                    break;
                    
                default:
                    throw new BierIntException("Operation not supported");
                    // break;
            }
        } catch (BierIntException e){
            p("Error: " + e.getMessage());
        }
    }
}