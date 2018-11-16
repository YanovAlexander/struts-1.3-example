package ua.com.struts.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    private static final Logger LOG = Logger.getLogger(PropertiesUtils.class);

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream resourceAsStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            LOG.error("loadProperties: Properties not found", e);
            throw new RuntimeException("Properties not found", e);
        }

        return properties;
    }
}
