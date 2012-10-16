package se.olenfalk.teamcity;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatisticsKeeperTest {
	@Test
	public final void testSorting() {
		StatisticsKeeper statisticsKeeper = new StatisticsKeeper();

		statisticsKeeper.addObservation("foo", 5000);
		statisticsKeeper.addObservation("bar", 8000);

		Statistics[] rendered = statisticsKeeper.getStatistics();
		assertEquals(2, rendered.length);
		assertEquals("bar", rendered[0].getName());
		assertEquals("foo", rendered[1].getName());

		statisticsKeeper.addObservation("fastest", 2000);

		rendered = statisticsKeeper.getStatistics();
		assertEquals(3, rendered.length);
		assertEquals("bar", rendered[0].getName());
		assertEquals("foo", rendered[1].getName());
		assertEquals("fastest", rendered[2].getName());
	}
}
