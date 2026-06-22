package com.flipkart.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE = "config/config.properties";

    static {
        try {
            properties = new Properties();
            InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
            if (is == null) {
                throw new RuntimeException("config.properties not found on classpath at " + CONFIG_FILE);
            }
            properties.load(is);
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read config.properties from classpath", e);
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key '" + key + "' not found in config.properties");
        }
        return value;
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static String getUrl() {
        return get("url");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(get("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(get("explicit.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(get("page.load.timeout"));
    }

    public static boolean isHeadless() { 
        String sysProp = System.getProperty("headless");
        if (sysProp != null) {
            return Boolean.parseBoolean(sysProp);
        }
        return Boolean.parseBoolean(get("headless"));
    }

    public static int getSlowMoSeconds() {
        try {
            return Integer.parseInt(properties.getProperty("slowmo.seconds", "0"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
