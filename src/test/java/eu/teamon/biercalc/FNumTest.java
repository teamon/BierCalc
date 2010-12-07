package eu.teamon.biercalc;

import org.junit.*;
import static org.junit.Assert.*;

public class FNumTest {
	@Test
	public void testContructor(){
		FNum a = new FNum(0, 1, new int[]{});
		assertArrayEquals(a.exp, new int[]{0,0,0,0,0,0,0,1});
		
		FNum b = new FNum(0, 100, new int[]{});
		assertArrayEquals(b.exp, new int[]{1,1,0,0,0,1,1,1});

		FNum c = new FNum(0, -5, new int[]{});
		assertArrayEquals(c.exp, new int[]{0,1,0,1,1,1,1,0});

		FNum d = new FNum(0, 0, new int[]{});
		assertArrayEquals(d.exp, new int[]{1,1,1,1,1,1,1,0});
	}
}

