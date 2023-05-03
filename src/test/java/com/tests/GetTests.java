package com.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.utils.APIBuilders.buildRequestForGetCalls;
import static org.assertj.core.api.Assertions.assertThat;

public class GetTests {
    @Test
    void testGetEmployeeDetails() {
        Response response = buildRequestForGetCalls()
                .baseUri("http://localhost:3000")
                .get("/employees");

        response.prettyPrint();
        assertThat(response.getStatusCode()).isEqualTo(200)
                .isNotEqualTo(201);
        assertThat(response.jsonPath().getList("$").size()).as("Validating size").isPositive()
                .as("Validating is greater then 30").isGreaterThan(30);
    }

    @Test
    void testGetEmployeeDetail() {
        Response response = buildRequestForGetCalls()
                .pathParams("id", "2")
                .get("employees/{id}");

        System.out.println(response.prettyPrint());

        assertThat(response.jsonPath().getString("lastName")).as("Validating last name").isEqualTo("Bhagat")
                .as("Validating size").hasSizeBetween(2, 10);
    }
}
