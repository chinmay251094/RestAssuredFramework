package com.utils;

import com.constants.FrameworkConstants;
import com.enums.PropertiesType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ConfigReaderUtils {
    private ConfigReaderUtils() {
    }

    private static final Properties properties = new Properties();
    private static final Map<String, String> map = new HashMap<>();

    static {
        try (FileInputStream fileInputStream = new FileInputStream(FrameworkConstants.getConfigPropertiesFilePath())) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.getCause();
            System.exit(0);
        }
        properties.forEach((key, value) -> map.put(String.valueOf(key), String.valueOf(value)));
    }

    public static String getValue(PropertiesType key) {
        return map.get(key.name().toLowerCase());
    }
}

//properties.entrySet().forEach(e -> map.put(String.valueOf(e.getKey()), String.valueOf(e.getValue())));

/*
for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            map.put(key, value);
        }
*/
