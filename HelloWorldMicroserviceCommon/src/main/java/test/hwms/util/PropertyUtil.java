package test.hwms.util;

public class PropertyUtil {
	public static int getIntProperty(String key, int defaultValue) {
		int result = defaultValue;
		
		String val = getProperty(key, null);
		if (val != null) {
			try {
				result = Integer.parseInt(val);
			} catch (NumberFormatException e) {
				// Ignore
			}
		}
		
		return result;
	}
	
	public static String getProperty(String key, String defaultValue) {
		// Check -D<key> command line properties
		String val = System.getProperty(key);
		if (val == null) {
			// If not set, check environment properties
			val = System.getenv(key);
		}
		
		return val == null ? defaultValue : val;
	}
	
	public static void main(String[] args) {
		System.out.println(getProperty("MATT", "not set"));
	}
}
