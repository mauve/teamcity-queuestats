package se.olenfalk.teamcity;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatisticsTest {
	@Test
	public void testFormatTimeDiff() {
		assertEquals("__:__:00s", Statistics.formatTimeDiff(0));

		assertEquals("__:__:00s", Statistics.formatTimeDiff(999));
		assertEquals("__:__:01s", Statistics.formatTimeDiff(1000));

		assertEquals("__:__:59s", Statistics.formatTimeDiff(1000 * 60 - 1));
		assertEquals("__:01:00s", Statistics.formatTimeDiff(1000 * 60));

		assertEquals("__:59:59s", Statistics.formatTimeDiff(1000 * 60 * 60 - 1));
		assertEquals("01:00:00s", Statistics.formatTimeDiff(1000 * 60 * 60));

		assertEquals("23:59:59s", Statistics.formatTimeDiff(1000 * 24 * 60 * 60 - 1));
		assertEquals("1d 00:00:00s", Statistics.formatTimeDiff(1000 * 24 * 60 * 60));
	}
}
