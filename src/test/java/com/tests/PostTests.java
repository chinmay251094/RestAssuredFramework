package com.tests;

import com.pojo.Employee;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.utils.APIBuilders.buildRequestForPostCalls;
import static com.utils.RandomUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PostTests {
    @Test
    void testPostCalls() {
        Employee employee = Employee.builder()
                .setId(getId())
                .setName(getName())
                .setEmail(getEmail())
                .setCity(getCity())
                .build();

        Response response = buildRequestForPostCalls(employee, "/employees");

        assertThat(response.getStatusCode()).isEqualTo(201);
    }
}
