package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {

	public String toReadDatafromPropertiesFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;

	}

}
