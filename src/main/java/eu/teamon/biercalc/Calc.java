package eu.teamon.biercalc;

import java.util.Arrays;
import static eu.teamon.biercalc.Util.*;

class CalcException extends Exception {
    public CalcException(String message){
        super(message);
    }
}


public class Calc {
	public static Num add(Num a, Num b) /*throws CalcException*/ {
		//if(a.base != b.base) throw new CalcException("Invalid base");
		
		int[] aBits;
		int[] bBits;
		int pointPos = 0;
		
		if(a.pointPos == b.pointPos){
			aBits = a.bits;
			bBits = b.bits;
			pointPos = a.pointPos;
		} else if(a.pointPos > b.pointPos){
			aBits = a.bits;
			bBits = new int[b.bits.length+(a.pointPos-b.pointPos)];
			System.arraycopy(b.bits, 0, bBits, a.pointPos-b.pointPos, b.bits.length);
			pointPos = a.pointPos;
		} else { // a.pointPos < b.pointPos
			bBits = b.bits;
			aBits = new int[a.bits.length+(b.pointPos-a.pointPos)];
			System.arraycopy(a.bits, 0, aBits, b.pointPos-a.pointPos, a.bits.length);
			pointPos = b.pointPos;
		}
		
		int size = Math.max(aBits.length, bBits.length) + 1;
		int sum[] = new int[size];
		int c = 0;
		
		for(int i=0; i<size; i++){
			int x = bitAt(a.base, aBits, i) + bitAt(a.base, bBits, i) + c;
			sum[i] = x % a.base;
			c = x / a.base;
		}
			
		return new Num(a.base, sum, pointPos);
	}
	
	public static Num subtract(Num a, Num b){
		return add(a, b.complacement());
	}
	
	public static FNum numToFNum(Num n){
		if(n.base != 2){
			return numToFNum(n); // TODO: Call n.toBase(2);
		}
		
		int sign;
		if(n.isNegative()){
			sign = 1;
			n = n.complacement();
		} else {
			sign = 0;
		}
				
		int[] man = new int[FNum.MAN_SIZE];
		int exp=0;

		int[] intPart = cutLeadingZero(n.intPartBits());
		int[] fractPart = n.fractPartBits();
		
		if(intPart.length == 1){
			if(intPart[0] == 1){
				System.arraycopy(fractPart, 0, man, 0, fractPart.length);
				exp = 0;
			} else { // intPart[0] == 0
				// liczba postaci 0.xxxxxx -> trzeba przesunac w prawo
				int p = leadingZeros(fractPart);
				System.arraycopy(fractPart, p, man, 0, fractPart.length-p);
				exp = -p;
			}
		} else {
			// przesuniecie przecinka w lewo
			int p = intPart.length-1;
			for(int i=0; i<p; i++){
				man[i] = intPart[p-i-1];
			}
			
			System.arraycopy(fractPart, 0, man, p, fractPart.length);			
			exp = p;
		}
		
		return new FNum(sign, exp, man);
	}
	
	protected static int leadingZeros(int[] fract){
		int i=0;
		for(;i<fract.length && fract[i]==0; i++);
		return i;
	}
	
	protected static int[] cutLeadingZero(int[] intp){
		if(intp.length == 1 || intp[intp.length-1] == 1){
			return intp;
		} else {
			int[] res = new int[intp.length-1];
			System.arraycopy(intp, 0, res, 0, res.length);
			return res;
		}
	}
	
	public static void main(String[] args){
		Num a = new Num(args[0]);
		FNum f = Calc.numToFNum(a);
		System.out.println(a);
		System.out.println(f);
//		Num b = new Num(args[1]);
//		Num c = Calc.subtract(a, b);
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);
	}
}
