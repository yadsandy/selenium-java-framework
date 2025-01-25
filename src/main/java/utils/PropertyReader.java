package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static String getConfigValue(String propertyName) {
        String propertyValue;
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return propertyValue;
    }
}
