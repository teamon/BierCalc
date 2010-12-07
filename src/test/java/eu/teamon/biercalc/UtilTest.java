package eu.teamon.biercalc;

import org.junit.*;
import static org.junit.Assert.*;
import static eu.teamon.biercalc.Util.*;

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


		assertArrayEquals(Util.baseToBase(2, new int[]{0,1,0, 0,1,1, 1,1}, 8), new int[]{2,6,7});
		assertArrayEquals(Util.baseToBase(2, new int[]{0,1,0, 0,1,1, 1,1}, 8, true), new int[]{2,3,6});

		assertArrayEquals(Util.baseToBase(2, new int[]{0}, 8), new int[]{0});
		assertArrayEquals(Util.baseToBase(2, new int[]{1}, 8), new int[]{7});
		assertArrayEquals(Util.baseToBase(2, new int[]{0}, 8, true), new int[]{0});
		assertArrayEquals(Util.baseToBase(2, new int[]{1}, 8, true), new int[]{4});
		

		assertArrayEquals(Util.baseToBase(8, new int[]{7,3}, 16), new int[]{15, 1});
		assertArrayEquals(Util.baseToBase(16, new int[]{10, 11}, 8), new int[]{2,7,6});
	}
	
	@Test
	public void testReverse(){
		assertArrayEquals(Util.reverse(new int[]{1,2,3,4,5}), new int[]{5,4,3,2,1});
	}
}
