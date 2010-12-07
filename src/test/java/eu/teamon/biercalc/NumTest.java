package eu.teamon.biercalc;

import org.junit.*;
import static org.junit.Assert.*;

public class NumTest {
	@Test 
	public void testStringToNum() {
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
	public void testComplacement(){
		Num a = new Num("011010111.10011");
		Num b = a.complacement();
		assertArrayEquals(b.bits, new int[]{1,0,1,1,0,0,0,0,1,0,1,0,0,1});
	}
	
	@Test
	public void testIsNegative(){
		Num a;
		a = new Num("011010111.10011");
		assertFalse(a.isNegative());
		
		a = new Num("111010111.10011");
		assertTrue(a.isNegative());
	}
	
	@Test
	public void testToString(){
		assertEquals(new Num("011010111.10011").toString(), "011010111.10011");
		assertEquals(new Num("111010111.0").toString(), "111010111.0");
	}
	
	@Test
	public void testEquals(){
		assertEquals(new Num("011011.1101"), new Num("011011.1101"));
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
