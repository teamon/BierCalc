package eu.teamon.biercalc;

public class Util {
    public static int[] bitsComplacement(int base, int[] bits){
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
    
    public static boolean isNegative(int base, int[] bits){
        return (bits.length != 0 && bits[bits.length-1] >= base/2);
   }
    
    public static int bitAt(int base, int[] bits, int index){
        if(index >= bits.length) {
            if(Util.isNegative(base, bits)) return base-1;
            else return 0;
        } else {
            return bits[index];
        }
	}
    
    public static int[] bitsCleanup(int base, int[] bits){
    	int i = 0;
    	
    	if(isNegative(base, bits)){
    		for(; i<bits.length && bits[bits.length-i-1] == base-1; i++);
    	} else {
        	for(; i<bits.length && bits[bits.length-i-1] == 0; i++);
    	}
    	
    	int[] clean = new int[bits.length-i+1];
    	System.arraycopy(bits, 0, clean, 0, bits.length-i+1);    	
 
    	return clean;
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
    
    
    public static void p(int a){
        System.out.println(a);
    }
    
    public static void p(String s, int a){
        System.out.println(s + " " + a);
    }
}