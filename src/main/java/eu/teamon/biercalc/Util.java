package eu.teamon.biercalc;

public class Util {
    public static int[] bitsComplacement(int base, int[] bits){
        int i;
        int[] c = new int[bits.length+1];

        for(i=0; i<c.length && bitAt(base, bits, i)==0; i++){
            c[i] = bitAt(base, bits, i);
        }

        c[i] = base - bitAt(base, bits, i);
        i++;

        for(; i<c.length; i++){
            c[i] = base - 1 - bitAt(base, bits, i);
        }
        
        return c;
    }
    
    public static boolean isNegative(int base, int[] bits){
        return (bits.length != 0 && bits[bits.length-1] >= base/2);
    }
    
    public static int bitAt(int base, int[] bits, int index){
    	return bitAt(base, bits, index, false);
    }
    
    public static int bitAt(int base, int[] bits, int index, boolean fillWithZeros){
        if(index >= bits.length) {
            if(!fillWithZeros && Util.isNegative(base, bits)) return base-1;
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
    	
    	if(i > 0){
        	int[] clean = new int[bits.length-i+1];
        	System.arraycopy(bits, 0, clean, 0, clean.length);    	
        	return clean;
    	} else {
    		return bits;
    	}
    }

    public static int[] intToBits(int n, int size){
    	int[] bits = new int[size];
    	for(int i=0; i<size; i++){
    		bits[i] = (n / (int)Math.pow(2, i)) % 2;
    	}
    	return bits;
    }
    
    public static int charToInt(char c){
		try {
			return Integer.parseInt("" + c);
		} catch (NumberFormatException e){
			return Integer.decode("0x"+c);
		}
	}
    
    
    public static final int[][] baseTo2 = new int[][]{
    	new int[]{0,0,0,0},
    	new int[]{1,0,0,0},
    	new int[]{0,1,0,0},
    	new int[]{1,1,0,0},
    	new int[]{0,0,1,0},
    	new int[]{1,0,1,0},
    	new int[]{0,1,1,0},
    	new int[]{1,1,1,0},
    	new int[]{0,0,0,1},
    	new int[]{1,0,0,1},
    	new int[]{0,1,0,1},
    	new int[]{1,1,0,1},
    	new int[]{0,0,1,1},
    	new int[]{1,0,1,1},
    	new int[]{0,1,1,1},
    	new int[]{1,1,1,1},
    };
    

    public static int[] baseToBase(int src, int[] bits, int dest){
    	return baseToBase(src, bits, dest, false);
    }
    
    public static int[] baseToBase(int src, int[] bits, int dest, boolean fillWithZeros){
    	if(src == dest) return bits;
    	
    	switch(src){
    		case 2:
    		{
    			switch(dest){
    				case 8:
    				{
    					int res[] = new int[(int)Math.ceil(bits.length/3.0)];
    					for(int i=0; i<res.length; i++){
    						if(fillWithZeros) res[i] = 4*bitAt(2, bits, 3*i, true) + 2*bitAt(2, bits, 3*i+1, true) + bitAt(2, bits, 3*i+2, true);
    						else res[i] = 4*bitAt(2, bits, 3*i+2) + 2*bitAt(2, bits, 3*i+1) + bitAt(2, bits, 3*i);
    					}
    					return res;
    				}
    				
    				case 16:
    				{
    					int res[] = new int[(int)Math.ceil(bits.length/4.0)];
    					for(int i=0; i<res.length; i++){
    						if(fillWithZeros) res[i] = 8*bitAt(2, bits, 4*i, true) + 4*bitAt(2, bits, 4*i+1, true) + 2*bitAt(2, bits, 4*i+2, true) + bitAt(2, bits, 4*i+3, true);
    						else res[i] = 8*bitAt(2, bits, 4*i+3) + 4*bitAt(2, bits, 4*i+2) + 2*bitAt(2, bits, 4*i+1) + bitAt(2, bits, 4*i);
        					}
    					return res;
    				}
    					
    				
    			}
    		}
    	
    		case 8:
    		{
    			switch(dest){
    				case 2:
    				{
    					int res[] = new int[bits.length*3];
    					for(int i=0; i<bits.length; i++){
    						System.arraycopy(baseTo2[bits[i]], 0, res, 3*i, 3);
    					}
    					return res;
    				}
    				case 16:
    				{
    					return baseToBase(2, baseToBase(8, bits, 2, fillWithZeros), 16, fillWithZeros);
    				}
    			}
    		}
    		
    		case 16:
    		{
    			switch(dest){
    				case 2:
    				{
    					int res[] = new int[bits.length*4];
    					for(int i=0; i<bits.length; i++){
    						System.arraycopy(baseTo2[bits[i]], 0, res, 4*i, 4);
    					}
    					return res;
    				}
    				case 8:
    				{
    					return baseToBase(2, baseToBase(16, bits, 2, fillWithZeros), 8, fillWithZeros);
    				}
    			}
    		}
    	
    		default:
    			return null;
    	}
    }
    
	public static int[] reverse(int[] bits){
		int[] res = new int[bits.length];
		
		for(int i=0; i<bits.length; i++){
			res[bits.length-i-1] = bits[i];
		}
		return res;
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