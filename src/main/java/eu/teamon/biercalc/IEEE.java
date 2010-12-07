package eu.teamon.biercalc;

import static eu.teamon.biercalc.Util.*;

class IEEEResult {
	public int sign;
	public int exp;
	public int[] man;
}

abstract class IEEE {	
	public int sign;
	public int[] exp;
	public int[] man;
	
	public IEEE(){
		this.sign = 0;
		this.exp = new int[expSize()];
		this.man = new int[manSize()];
	}
	
	public IEEE(int sign, int[] exp, int[] man){
		this.sign = sign;
		this.exp = exp;
		this.man = man;
	}
	
	public IEEE(int sign, int exp, int[] man){
		this.sign = sign;
		this.exp = calculateExp(exp);
		this.man = man;
	}
	
	public IEEE(IEEEResult res){
		this(res.sign, res.exp, res.man);
	}
	
	public IEEE(String str){
		this();
		String[] chunks = str.split(" ");
		
		this.sign = charToInt(chunks[0].charAt(0));
		
		for(int i=0; i<expSize(); i++){
			this.exp[i] = charToInt(chunks[1].charAt(expSize()-i-1));
		}
		
		for(int i=0; i<manSize(); i++){
			this.man[i] = charToInt(chunks[2].charAt(i));
		}
	}
	
	public String toString(){
		StringBuffer buf = new StringBuffer();
		
		buf.append(sign);
		buf.append(" ");
		for(int i=0; i<exp.length; i++) buf.append(exp[exp.length-i-1]);
		buf.append(" ");
		for(int i=0; i<man.length; i++) buf.append(man[i]);
		return buf.toString();
	}
	
	protected int[] calculateExp(int e){
		return Util.intToBits(e - 1 + (int)Math.pow(2, expSize()-1), expSize());
	}
	
    public boolean equals(Object that){
    	return (that instanceof IEEE && this.toString().equals(that.toString()));
    }
	
	abstract public int expSize();
	abstract public int manSize();
}

class DoubleNum extends IEEE {	
	public static final int EXP_SIZE = 11;
	public static final int MAN_SIZE = 52;
	
	public DoubleNum(){
		super();
	}
	
	public DoubleNum(int s, int[] e, int[] m){
		super(s, e, m);
	}
	
	public DoubleNum(IEEEResult res){
		super(res);
	}
	
	public DoubleNum(int sign, int exp, int[] man){
		super(sign, exp, man);
	}
	
	public DoubleNum(String str){
		super(str);
	}
		
	public int expSize(){ return EXP_SIZE; }
	public int manSize(){ return MAN_SIZE; }
}

class SingleNum extends IEEE {	
	public static final int EXP_SIZE = 8;
	public static final int MAN_SIZE = 23;
	
	public SingleNum(){
		super();
	}
	
	public SingleNum(int s, int[] e, int[] m){
		super(s, e, m);
	}
	
	public SingleNum(IEEEResult res){
		super(res);
	}
	
	public SingleNum(int s, int e, int[] m){
		super(s, e, m);
	}
	
	public SingleNum(String str){
		super(str);
	}
	
	public int expSize(){ return EXP_SIZE; }
	public int manSize(){ return MAN_SIZE; }
}


