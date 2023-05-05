package com.constants;

import lombok.Getter;

public class FrameworkConstants {
    /**
     * If variable/method is non-static then have @Getters at class level else at field level
     */
    private static @Getter
    final String requestJsonFilePath = System.getProperty("user.dir") + "/src/test/resources/json/";
    private static @Getter
    final String responseJsonFilePath = System.getProperty("user.dir") + "/output/";
    private static @Getter
    final String configPropertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
}
