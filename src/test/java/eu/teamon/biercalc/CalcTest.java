package eu.teamon.biercalc;

import org.junit.*;
import static org.junit.Assert.*;

public class CalcTest {
	@Test
	public void testAdd(){
		assertEquals(Calc.add(new Num("01001"), new Num("0110")), new Num("01111"));
		assertEquals(Calc.add(new Num("111.11"), new Num("0.01")), new Num("0"));
		assertEquals(Calc.add(new Num("0111.11"), new Num("0.01")), new Num("01000.00"));	
	}
	
	@Test
	public void testSubtract(){
		assertEquals(Calc.subtract(new Num("01111"), new Num("01001")), new Num("0110"));
		assertEquals(Calc.subtract(new Num("0"), new Num("111.11")), new Num("0.01"));
		assertEquals(Calc.subtract(new Num("01000.00"), new Num("0111.11")), new Num("0.01"));	
	}
	
	@Test
	public void testCutLeadingZero(){
		assertArrayEquals(Calc.cutLeadingZero(new int[]{0}), new int[]{0});
		assertArrayEquals(Calc.cutLeadingZero(new int[]{0,1}), new int[]{0,1});
		assertArrayEquals(Calc.cutLeadingZero(new int[]{1,0}), new int[]{1});
	}
}
