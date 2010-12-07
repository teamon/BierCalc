package eu.teamon.biercalc;

import org.junit.*;
import static org.junit.Assert.*;

public class UtilTest {
	@Test
	public void testIntToBits(){
		assertArrayEquals(Util.intToBits(1, 8), new int[]{1,0,0,0,0,0,0,0});
		assertArrayEquals(Util.intToBits(4, 8), new int[]{0,0,1,0,0,0,0,0});
		assertArrayEquals(Util.intToBits(127, 8), new int[]{1,1,1,1,1,1,1,0});
	}
	
	@Test
	public void testBaseToBase(){
		assertArrayEquals(Util.baseToBase(8, new int[]{2,6,7}, 2), new int[]{0,1,0, 0,1,1, 1,1,1});
		assertArrayEquals(Util.baseToBase(8, new int[]{0,1,3}, 2), new int[]{0,0,0, 1,0,0, 1,1,0});
		
		assertArrayEquals(Util.baseToBase(16, new int[]{2,15,7}, 2), new int[]{0,1,0,0, 1,1,1,1, 1,1,1,0});
		assertArrayEquals(Util.baseToBase(16, new int[]{0,1,13}, 2), new int[]{0,0,0,0, 1,0,0,0, 1,0,1,1});
		

		assertArrayEquals(Util.baseToBase(2, new int[]{0,1,0, 0,1,1, 1,1,1}, 8), new int[]{2,6,7});
		assertArrayEquals(Util.baseToBase(2, new int[]{0,0,0, 1,0,0, 1,1,0}, 8), new int[]{0,1,3});
		
		assertArrayEquals(Util.baseToBase(2, new int[]{0,1,0,0, 1,1,1,1, 1,1,1,0, 0,0,0,0}, 16), new int[]{2,15,7,0});
		assertArrayEquals(Util.baseToBase(2, new int[]{0,0,0,0, 1,0,0,0, 1,0,1}, 16), new int[]{0,1,13});
	}
}
