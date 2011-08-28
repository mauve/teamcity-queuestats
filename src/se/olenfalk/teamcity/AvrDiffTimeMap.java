package se.olenfalk.teamcity;

import java.util.HashMap;
import java.util.Map;

public class AvrDiffTimeMap extends HashMap<String, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Long put(String key, Long value) {
		if (!containsKey(key))
			return super.put(key, value);
		
		Long currentTime = super.get(key);
		return super.put(key, (currentTime + value) / 2);
	}

	public Map<String, String> getFormattedMap() {
		HashMap<String, String> result = new HashMap<String, String>();
		
		for (Map.Entry<String, Long> entry : super.entrySet()) {
			result.put(entry.getKey(), formatTimeDiff(entry.getValue()));
		}
		
		return result;
	}
	
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
}
