package com.tests;

import com.annotations.TestDescription;
import com.enums.Author;
import com.enums.Category;
import com.reports.Logger;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.utils.APIBuilders.buildRequestForGetCalls;
import static org.assertj.core.api.Assertions.assertThat;

public class GetTests {
    @Test
    @TestDescription(description = "To get all employee details", author = Author.CHINMAY, category = Category.SMOKE)
    void testGetEmployeeDetails() {
        Response response = buildRequestForGetCalls()
                .baseUri("http://localhost:3000")
                .get("/employees");

        response.prettyPrint();
        Logger.logResponse(response.prettyPrint());

        assertThat(response.getStatusCode()).isEqualTo(200)
                .isNotEqualTo(201);
        assertThat(response.jsonPath().getList("$").size()).as("Validating size").isPositive()
                .as("Validating is greater then 30").isGreaterThan(30);
    }

    @Test(dataProvider = "getData")
    @TestDescription(description = "To get an employee detail", author = Author.CHINMAY, category = Category.SMOKE)
    void testGetEmployeeDetail(String id, String lastName) {
        Response response = buildRequestForGetCalls()
                .pathParams("id", id)
                .get("employees/{id}");

        System.out.println(response.prettyPrint());

        assertThat(response.jsonPath().getString("lastName")).as("Validating last name").isEqualTo(lastName)
                .as("Validating size").hasSizeBetween(2, 10);
    }

    @DataProvider
    Object[][] getData() {
        return new Object[][]{
                {"2", "Bhagat"},
                {"3", "Darji"}
        };
    }
}
