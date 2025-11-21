package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }
}