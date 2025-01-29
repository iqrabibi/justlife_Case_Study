package com.justlife.stepDefinations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class config {
    private static Properties properties = null;

    // Load properties once
    public static void loadProperties() throws IOException {
        if (properties == null) {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/testdata.properties");
            properties.load(fileInputStream);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
