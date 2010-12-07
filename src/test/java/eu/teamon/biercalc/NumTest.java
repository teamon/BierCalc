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
		
		Num c = new Num("0.01");
		assertTrue(c.pointPos == 2);
		assertArrayEquals(c.bits, new int[]{1,0});
		assertArrayEquals(c.intPartBits(), new int[]{0});
		assertArrayEquals(c.fractPartBits(), new int[]{0,1});
		

		Num d = new Num("0");
		assertTrue(d.pointPos == 0);
		assertArrayEquals(d.bits, new int[]{0});
		assertArrayEquals(d.intPartBits(), new int[]{0});
		assertArrayEquals(d.fractPartBits(), new int[]{});
		

		Num e = new Num("111.11");
		assertTrue(e.pointPos == 2);
		assertArrayEquals(e.bits, new int[]{1});
		assertArrayEquals(e.intPartBits(), new int[]{1});
		assertArrayEquals(e.fractPartBits(), new int[]{1,1});
	}
	
	@Test
	public void testComplacement(){
		assertEquals(new Num("011010111.10011").complacement(), new Num("100101000.01101"));
		assertEquals(new Num("111.11").complacement(), new Num("000.01"));
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
		assertEquals(new Num("111010111.0").toString(), "1010111.0");
		assertEquals(new Num("0").toString(), "0.0");
		assertEquals(new Num("1").toString(), "1.0");
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
