package com.example.place_suggestor.util;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static Logger logger = Logger.getLogger(PropertyReader.class);

    public static String getPropertyValue(String propertyName) {
        String propFileName = "application.properties";
        try (InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propFileName)) {
            Properties prop = new Properties();
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            return prop.getProperty(propertyName);
        } catch (Exception e) {
            logger.error("Parsing properties exception", e);
        }
        return null;
    }
}