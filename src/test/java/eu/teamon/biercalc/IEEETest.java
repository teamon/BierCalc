package eu.teamon.biercalc;

import org.junit.*;
import static org.junit.Assert.*;

public class IEEETest {
	@Test
	public void testContructor(){
		SingleNum a = new SingleNum(0, 1, new int[]{});
		assertArrayEquals(a.exp, new int[]{0,0,0,0,0,0,0,1});
		
		SingleNum b = new SingleNum(0, 100, new int[]{});
		assertArrayEquals(b.exp, new int[]{1,1,0,0,0,1,1,1});

		SingleNum c = new SingleNum(0, -5, new int[]{});
		assertArrayEquals(c.exp, new int[]{0,1,0,1,1,1,1,0});

		SingleNum d = new SingleNum(0, 0, new int[]{});
		assertArrayEquals(d.exp, new int[]{1,1,1,1,1,1,1,0});
	}
}

