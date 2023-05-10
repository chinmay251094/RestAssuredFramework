package com.base;

import com.supplier.TestDataSupplier;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    @BeforeMethod
    public void setUp(Object[] data) {
        // Create a map to store the test data
        Map<String, TestDataSupplier> testDataMap = new HashMap<>();

        // Loop through the test data and add it to the map
        for (Object obj : data) {
            TestDataSupplier dataSupplier = (TestDataSupplier) obj;
            testDataMap.put(dataSupplier.getTestcase(), dataSupplier);
        }

        // Get the test data supplier for the first test case in the data array
        TestDataSupplier testDataSupplier = testDataMap.get(((TestDataSupplier) data[0]).getTestcase());
    }
}
