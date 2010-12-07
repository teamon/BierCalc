package eu.teamon.biercalc;

import org.junit.*;
import static org.junit.Assert.*;

public class NumTest {
	@Test 
	public void stringToNum() {
		Num a = new Num("011010111.10011");
		assertTrue(a.pointPos == 5);
		assertArrayEquals(a.bits, new int[]{1,1,0,0,1,1,1,1,0,1,0,1,1,0});
		assertArrayEquals(a.intPartBits(), new int[]{1,1,1,0,1,0,1,1,0});
		assertArrayEquals(a.fractPartBits(), new int[]{1,0,0,1,1});
		
		Num b = new Num("011010111");
		assertTrue(b.pointPos == 0);
		assertArrayEquals(b.bits, new int[]{1,1,1,0,1,0,1,1,0});
		assertArrayEquals(b.intPartBits(), new int[]{1,1,1,0,1,0,1,1,0});
		assertArrayEquals(b.fractPartBits(), new int[]{});
	}
	
	@Test
	public void complacement(){
		Num a = new Num("011010111.10011");
		Num b = a.complacement();
		assertArrayEquals(b.bits, new int[]{1,0,1,1,0,0,0,0,1,0,1,0,0,1});
	}
	
	@Test
	public void isNegative(){
		Num a;
		a = new Num("011010111.10011");
		assertFalse(a.isNegative());
		
		a = new Num("111010111.10011");
		assertTrue(a.isNegative());
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
}
