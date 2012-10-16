package se.olenfalk.teamcity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StatisticsKeeper {
	private Map<String, Statistics> statisticsMap = new HashMap<String, Statistics>();

	/**
	 * Add an observation for a named node.
	 *
	 * @param name The node name.
	 * @param timeDiffMs A queue time observation.
	 */
	public void addObservation(String name, long timeDiffMs) {
		Statistics statistics = statisticsMap.get(name);
		if (statistics == null) {
			statistics = new Statistics(name);
			statisticsMap.put(name, statistics);
		}

		statistics.addObservation(timeDiffMs);
	}

	public Statistics[] getStatistics() {
		Statistics statistics[] = new Statistics[statisticsMap.size()];
		int i = 0;
		for (Map.Entry<String, Statistics> entry : statisticsMap.entrySet()) {
			statistics[i++] = entry.getValue();
		}

		Arrays.sort(statistics);
		return statistics;
	}
}
