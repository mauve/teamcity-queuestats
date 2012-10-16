package se.olenfalk.teamcity;

/**
 * Contains statistics to be displayed in a table row, can be for an agent, a job or a project.
 *
 * @author Johan Walles, walles@spotify.com
 */
public class Statistics implements Comparable<Statistics> {
	public Statistics(String name) {
		if (name == null) {
			throw new NullPointerException("Name must not be null");
		}
		if (name.length() == 0) {
			throw new IllegalArgumentException("Name must not be empty");
		}
		this.name = name;
	}

	public void addObservation(long timeDiffMs) {
		totalQueueTime += timeDiffMs;
		count++;

		if (timeDiffMs > maxQueueTime) {
			maxQueueTime = timeDiffMs;
		}
	}

	private String name;
	private long maxQueueTime;
	private long totalQueueTime;
	private long count;

	private static String formatTimeDiff(long diff) {
        final int ONE_DAY = 1000 * 60 * 60 * 24;
        final int ONE_HOUR = ONE_DAY / 24;
        final int ONE_MINUTE = ONE_HOUR / 60;
        final int ONE_SECOND = ONE_MINUTE / 60;

        String result = new String();
        result  = (diff / ONE_DAY) + "d ";
        diff %= ONE_DAY;

        result += (diff / ONE_HOUR) + "h ";
        diff %= ONE_HOUR;

        result += (diff / ONE_MINUTE) + "m ";
        diff %= ONE_MINUTE;

        result += (diff / ONE_SECOND) + "s ";
        return result;
	}

	public String getName() {
		return name;
	}

	public String getAverageQueueTime() {
		return formatTimeDiff(averageQueueTime());
	}

	private long averageQueueTime() {
		return totalQueueTime / count;
	}

	public String getMaxQueueTime() {
		return formatTimeDiff(maxQueueTime);
	}

	public String getCount() {
		return Long.toString(count);
	}

	@Override
	public int compareTo(Statistics other) {
		int result = new Long(averageQueueTime()).compareTo(other.averageQueueTime());
		if (result != 0) {
			return result;
		}

		return name.compareTo(other.name);
	}
}
