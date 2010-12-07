package eu.teamon.biercalc;

public class FNum {
	public static final int EXP_SIZE = 8;
	public static final int MAN_SIZE = 23;
	public int sign;
	public int[] exp;
	public int[] man;
	
	public FNum(){
		this.sign = 0;
		this.exp = new int[EXP_SIZE];
		this.man = new int[MAN_SIZE];
	}
	
	public FNum(int sign, int[] exp, int[] man){
		this.sign = sign;
		this.exp = exp;
		this.man = man;
	}
	
	public FNum(int sign, int exp, int[] man){
		this.sign = sign;
		this.exp = Util.intToBits(exp - 1 + (int)Math.pow(2, EXP_SIZE-1), EXP_SIZE);
		this.man = man;
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
}
