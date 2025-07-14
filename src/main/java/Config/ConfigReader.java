package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

	//  Reads values from the config.properties file (e.g., URL, browser type).

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            // Load config.properties file
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    // Method to get property values
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
