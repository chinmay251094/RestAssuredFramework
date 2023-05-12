package com.tests;

import com.annotations.TestDescription;
import com.enums.Author;
import com.enums.Category;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.utils.APIBuilders.buildRequestForDeleteCalls;


public class DeleteTests {
    @Test
    @TestDescription(description = "To delete an employee", author = Author.CHINMAY, category = Category.SMOKE)
    void testDeleteEmployee() {
        // Create the request specification for DELETE call
        RequestSpecification request = buildRequestForDeleteCalls()
                .pathParam("id", "78");

        // Send the DELETE request
        Response response = request.delete("/employees/{id}");

        // Print the response
        response.prettyPrint();

        // Assert the response status code
        int statusCode = response.getStatusCode();
        Assertions.assertThat(statusCode).isEqualTo(204)
                .withFailMessage("Expected status code 204 but got " + statusCode);
    }
}
