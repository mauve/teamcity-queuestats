package com.spotify.teamcity;

import static org.junit.Assert.*;

import org.junit.Test;

public class AvrDiffTimeMapTest {

	@Test
	public final void testPutStringLong() {
		AvrDiffTimeMap map = new AvrDiffTimeMap();
		
		map.put("A", (long)100);
		map.put("B", (long)200);
		
		assertEquals(2, map.size());
		assertEquals((long)map.get("A"), (long)100);
		assertEquals((long)map.get("B"), (long)200);
	}

	/*@Test
	public final void testGetFormattedMap() {
		fail("Not yet implemented");
	}*/

}
