package com.tests;

import com.annotations.TestDescription;
import com.enums.Author;
import com.enums.Category;
import com.pojo.Employee;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.utils.APIBuilders.buildRequestForPutCalls;
import static com.utils.RandomUtils.*;

public class PutTests {
    @Test
    @TestDescription(description = "To update an employee", author = Author.CHINMAY, category = Category.SMOKE)
    void testPutRequest() {
        Employee employee = Employee.builder()
                .setId(getId())
                .setName(getName())
                .setEmail(getEmail())
                .setCity(getCity())
                .build();

        Response response = buildRequestForPutCalls().body(employee).put("/employees/{id}", employee.getId());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
    }
}
