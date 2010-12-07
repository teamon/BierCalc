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
}
