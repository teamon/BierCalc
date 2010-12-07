package eu.teamon.biercalc;

class Util {
    public static int[] bitsComplacement(int[] bits, int base){
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
}

public class Num {
	public static final int DEFAULT_BASE = 2;
	public int[] bits;
	public int pointPos;
	
	public int base;
	
	public Num(String str){
		this(DEFAULT_BASE, str);
	}
		
	public Num(int base, String str){
		this.base = base;
		int pos = str.indexOf('.');
		int len = str.length();
		int[] bits;
		
		if(pos == -1){
			this.pointPos = 0;
			bits = new int[len];
			    
			    	for(int i=0; i<len; i++){
			            bits[i] = parseChar(str.charAt(i));
			        }			
		} else {
			this.pointPos = len-pos-1;
			bits = new int[len-1];
			
			for(int i=0; i<len; i++){
				if(i < pos){
					bits[i] = parseChar(str.charAt(i));
				} else if(i > pos){
					bits[i-1] = parseChar(str.charAt(i));
				}
			}
		}
		
		this.bits = reverse(bits);
	}

	
	public Num(int base, int[] bits, int pos){
		this.base = base;
		this.bits = bits;
		this.pointPos = pos;
	}
	
	public Num(int[] bits, int pos){
		this(DEFAULT_BASE, bits, pos);
	}
	
	int[] intPartBits(){
		int len = this.bits.length - pointPos;
		int[] part = new int[len];
		for(int i=0; i<len; i++){
			part[i] = this.bits[pointPos+i];
		}
		
		return part;
	}
	
	int[] fractPartBits(){
		int[] part = new int[pointPos];
		for(int i=0; i<pointPos; i++){
			part[pointPos-i-1] = this.bits[i];
		}
			
		return part;
	}
	
	Num complacement(){
		return new Num(Util.bitsComplacement(this.bits, this.base), this.pointPos);
	}
	
    public boolean isNegative(){
        if(bits.length != 0) return bits[bits.length-1] >= this.base/2;
        else return false;
    }
	

	protected int parseChar(char c){
		try {
			return Integer.parseInt("" + c);
		} catch (NumberFormatException e){
			return Integer.decode("0x"+c);
		}
	}
	
	protected int[] reverse(int[] tab){
		int[] res = new int[tab.length];
		
		for(int i=0; i<tab.length; i++){
			res[tab.length-i-1] = tab[i];
		}
		return res;
	}

}