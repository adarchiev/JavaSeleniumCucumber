package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
	private static Properties property;
	static {
		try {
			String path = "app.properties";
			FileInputStream input = new FileInputStream(path);
			property = new Properties();
			property.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String keyName) {
		return property.getProperty(keyName);
	}
}
