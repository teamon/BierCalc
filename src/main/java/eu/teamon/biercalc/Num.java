package eu.teamon.biercalc;

import static eu.teamon.biercalc.Util.*;

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
		cleanup();
	}

	
	public Num(int base, int[] bits, int pos){
		this.base = base;
		this.bits = bits;
		this.pointPos = pos;
		cleanup();
	}
	
	public Num(int[] bits, int pos){
		this(DEFAULT_BASE, bits, pos);
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
	
    public boolean equals(Object that){
    	return (that instanceof Num && this.toString().equals(that.toString()));
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