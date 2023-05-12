package com.base;

import com.supplier.ApiTestDataSupplier;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    @BeforeMethod
    public void setUp(Object[] data) {
        // Create a map to store the test data
        Map<String, ApiTestDataSupplier> testDataMap = new HashMap<>();

        // Loop through the test data and add it to the map
        for (Object obj : data) {
            ApiTestDataSupplier dataSupplier = (ApiTestDataSupplier) obj;
            testDataMap.put(dataSupplier.getTestcase(), dataSupplier);
        }
    }
}
