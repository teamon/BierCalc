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
}
