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
		
		this.bits = bitsCleanup(2, reverse(bits));
	}

	
	public Num(int base, int[] bits, int pos){
		this.base = base;
		this.bits = bitsCleanup(2, bits);
		this.pointPos = pos;
	}
	
	public Num(int[] bits, int pos){
		this(DEFAULT_BASE, bits, pos);
	}
	
	int[] intPartBits(){
		p(this.bits.length);
		p(pointPos);
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
		return new Num(Util.bitsComplacement(this.base, this.bits), this.pointPos);
	}
	
    public boolean isNegative(){
        return Util.isNegative(this.base, this.bits);
    }
    
    public String toString(){
    	StringBuffer buf = new StringBuffer();
    	
    	for(int i : intPartBits()) buf.append(i);
    	buf = buf.reverse();
    	buf.append('.');
     	for(int i : fractPartBits()) buf.append(i);
      	
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

}