package com.tests;

import com.annotations.TestDescription;
import com.constants.ConstantsWithSingleton;
import com.enums.Author;
import com.enums.Category;
import com.pojo.Employee;
import com.utils.APIUtils;
import com.utils.DateTimeUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.constants.FrameworkConstants.getRequestJsonFilePath;
import static com.constants.FrameworkConstants.getResponseJsonFilePath;
import static com.reports.Logger.logRequest;
import static com.reports.Logger.logResponse;
import static com.utils.APIBuilders.buildRequestForPostCalls;
import static com.utils.APIUtils.readJsonAndGetAsString;
import static com.utils.RandomUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PostTests {
    @Test
    @TestDescription(description = "To post a new employee", author = Author.CHINMAY, category = Category.SMOKE)
    void testPostCalls() {
        Employee employee = Employee.builder()
                .setId(getId())
                .setName(getName())
                .setEmail(getEmail())
                .setCity(getCity())
                .build();

        Response response = buildRequestForPostCalls().body(employee).post("/employees");

        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    @TestDescription(description = "To post a new employee using external file", author = Author.CHINMAY, category = Category.SMOKE)
    void testPostRequestUsingExternalFile(Method method) {
        String uid = String.valueOf(getId());
        String fname = getName();
        String resource = readJsonAndGetAsString(getRequestJsonFilePath() + method.getName() + ".json")
                .replace("uid", uid)
                .replace("fname", fname);

        RequestSpecification requestSpecification = buildRequestForPostCalls().body(resource);

        Response response = requestSpecification.post("/employees");

        response.prettyPrint();

        APIUtils.storeDataAsJsonFile(getResponseJsonFilePath() + method.getName() + DateTimeUtils.generateDateTime() + ".json", response);

        assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    @TestDescription(description = "To post a new employee using external file", author = Author.CHINMAY, category = Category.SMOKE)
    void testPostRequestUsingExternalFileSingleton(Method method) {
        String uid = String.valueOf(getId());
        String fname = getName();
        String resource = readJsonAndGetAsString(ConstantsWithSingleton.getInstance().getRequestJsonFilePath()
                + method.getName() + ".json")
                .replace("uid", uid)
                .replace("fname", fname);

        RequestSpecification requestSpecification = buildRequestForPostCalls().body(resource);
        Response response = requestSpecification.post("/employees");
        logRequest(requestSpecification);
        logResponse(response);
        response.prettyPrint();

        APIUtils.storeDataAsJsonFile(ConstantsWithSingleton.getInstance().getResponseJsonFilePath()
                + method.getName() + DateTimeUtils.generateDateTime() + ".json", response);

        assertThat(response.getStatusCode()).isEqualTo(201);
    }
}
