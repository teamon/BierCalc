package eu.teamon.biercalc;

import static eu.teamon.biercalc.Util.*;

public class Num {
	public static final int DEFAULT_BASE = 2;
	public int[] bits;
	public int pointPos;
	
	public int base;
	
	public Num(){
		this(DEFAULT_BASE);
	}
	
	public Num(int base){
		this.base = base;
		this.bits = new int[0];
	}
	
	public Num(String str){
		this(DEFAULT_BASE, str);
	}
		
	public Num(int base, String str){
		this(base);
		int pos = str.indexOf('.');
		int len = str.length();
		int[] bits;
		
		if(pos == -1){
			this.pointPos = 0;
			bits = new int[len];
			    
			for(int i=0; i<len; i++){
				bits[i] = charToInt(str.charAt(i));
			}			
		} else {
			this.pointPos = len-pos-1;
			bits = new int[len-1];
			
			for(int i=0; i<len; i++){
				if(i < pos){
					bits[i] = charToInt(str.charAt(i));
				} else if(i > pos){
					bits[i-1] = charToInt(str.charAt(i));
				}
			}
		}
				
		this.bits = reverse(bits);
		cleanup();
	}

	
	public Num(int b, int[] bits, int pos){
		this(b);
		this.bits = bits;
		this.pointPos = pos;
		cleanup();
	}
	
	public Num(int[] bits, int pos){
		this(DEFAULT_BASE, bits, pos);
	}
	
	
	public Num(int b, int[] intBits, int[] fractBits){
		this(b);
		
		this.bits = new int[intBits.length + fractBits.length];
		int[] fr = reverse(fractBits);
		System.arraycopy(fr, 0, bits, 0, fr.length);
		System.arraycopy(intBits, 0, bits, fr.length, intBits.length);
		
		this.pointPos = fr.length;
		cleanup();
	}
	
	public Num(int[] intBits, int[] fractBits){
		this(DEFAULT_BASE, intBits, fractBits);
	}
	
	
	int[] intPartBits(){
		if(bits.length == pointPos){
			return new int[]{0};
		} else if(bits.length < pointPos){
			return new int[]{bitAt(base, bits, bits.length)};
		} else {
			int len = bits.length - pointPos;
			int[] part = new int[len];
			for(int i=0; i<len; i++){
				part[i] = bits[pointPos+i];
			}
			
			return part;
		}
	}
	
	int[] fractPartBits(){
		int[] part = new int[pointPos];
		for(int i=0; i<pointPos; i++){
			part[pointPos-i-1] = bitAt(base, bits, i);
		}
			
		return part;
	}
	
	Num complacement(){
		return new Num(Util.bitsComplacement(base, bits), pointPos);
	}
	
    public boolean isNegative(){
        return Util.isNegative(base, bits);
    }
    
    public String toString(){
    	StringBuffer buf = new StringBuffer();
    	
    	for(int i : intPartBits()) buf.append(i);
    	buf = buf.reverse();
    	buf.append('.');
    	
    	int[] fract = fractPartBits();
    	if(fract.length == 0){
    		buf.append(0);
    	} else {
    		for(int i : fractPartBits()) buf.append(i);
    	}
    	return buf.toString();
    }
    
    public Num toBase(int b){
    	int[] intp = baseToBase(base, intPartBits(), b);
    	int[] fractp = reverse(baseToBase(base, reverse(fractPartBits()), b, true));
    	
    	return new Num(b, intp, fractp);
    }
	
    public boolean equals(Object that){
    	return (that instanceof Num && this.toString().equals(that.toString()));
    }
	

	protected void cleanup(){
		this.bits = bitsCleanup(base, bits);
		fractPartCleanup();
	}
	
	protected void fractPartCleanup(){
		int[] fract = fractPartBits();
		
		int i=0;
		for(; i<fract.length && fract[fract.length-i-1]==0; i++);
		if(i > 0){
			this.pointPos -= i;
			if(bits.length > i){
				int[] nbits = new int[bits.length-i];
				System.arraycopy(bits, i, nbits, 0, bits.length-i);
				this.bits = nbits;	
			}
		}

	}

}