package com.constants;

import lombok.Getter;

@Getter
public class ConstantsWithSingleton { //Singleton --> a single instance for a class at a particular time
    private static ConstantsWithSingleton INSTANCE = null;

    private ConstantsWithSingleton() {
    }

    public static synchronized ConstantsWithSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConstantsWithSingleton();
        }
        return INSTANCE;
    }

    private final String requestJsonFilePath = System.getProperty("user.dir") + "/src/test/resources/json/";
    private final String responseJsonFilePath = System.getProperty("user.dir") + "/output/";
}
