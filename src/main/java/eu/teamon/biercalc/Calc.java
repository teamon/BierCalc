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
		
		p(a.pointPos);
		p(b.pointPos);
				
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
		
		p(aBits);
		p(bBits);
		p(sum);
		
		return new Num(a.base, sum, pointPos);
	}
	
	
	public static void main(String[] args){
//		Num a = new Num(args[0]);
//		Num b = new Num(args[1]);
//		Num c = Calc.add(a, b);
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(c);

		int[] a = {1,0,1,1,1,1,1};
		int[] b = {1,0,1,0,1,0,0,1,0,0,0,0};
		
		bitsCleanup(2, a);
		bitsCleanup(2, b);
	}
}
