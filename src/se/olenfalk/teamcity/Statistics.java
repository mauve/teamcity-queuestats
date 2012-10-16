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

	static String formatTimeDiff(long diffMs) {
        final long ONE_DAY = 1000 * 60 * 60 * 24;
        final long ONE_HOUR = ONE_DAY / 24;
        final long ONE_MINUTE = ONE_HOUR / 60;
        final long ONE_SECOND = ONE_MINUTE / 60;

        long days = diffMs / ONE_DAY;
        diffMs %= ONE_DAY;

        long hours = diffMs / ONE_HOUR;
        diffMs %= ONE_HOUR;

        long minutes = diffMs / ONE_MINUTE;
        diffMs %= ONE_MINUTE;

        long seconds = diffMs / ONE_SECOND;

        if (days > 0) {
			return String.format("%dd %02d:%02d:%02ds", days, hours, minutes, seconds);
        } else if (hours > 0) {
			return String.format("%02d:%02d:%02ds", hours, minutes, seconds);
        } else if (minutes > 0) {
			return String.format("__:%02d:%02ds", minutes, seconds);
        } else {
			return String.format("__:__:%02ds", seconds);
        }
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
			// Return negative result to get the slowest ones first
			return -result;
		}

		return name.compareTo(other.name);
	}
}
