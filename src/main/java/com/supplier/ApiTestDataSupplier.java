package com.supplier;

import com.creditdatamw.zerocell.annotation.Column;
import com.creditdatamw.zerocell.converter.BooleanConverter;
import io.github.sskorol.data.Sheet;
import lombok.Getter;

/**
 * This class represents a data supplier for test data.
 * <p>
 * The data is read from an Excel sheet named "TestData".
 */
@Sheet(name = "TestData")
public class ApiTestDataSupplier {
    @Column(name = "TestCase", index = 0)
    private String TestCase;
    @Column(name = "Description", index = 1)
    private String Description;
    @Column(name = "URL", index = 2)
    private String URL;
    @Column(name = "Execute", index = 3, converterClass = BooleanConverter.class)
    private boolean Execute;
    @Column(name = "Mode", index = 4)
    private String Mode;
    @Column(name = "Name", index = 5)
    private String Name;

    public String getTestcase() {
        return TestCase;
    }

    public boolean isExecute() {
        return Execute;
    }

    public String getDescription() {
        return Description;
    }

    public String getURL() {
        return URL;
    }

    public String getName() {
        return Name;
    }
}
