package com.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public final class APIBuilders {
    private APIBuilders() {}

    public static RequestSpecification buildRequestForGetCalls() {
        return given().log().all()
                .baseUri("http://localhost:3000");
    }

    public static Response buildRequestForPostCalls(Object obj, String uriPath) {
        return given().log().all()
                .baseUri("http://localhost:3000")
                .contentType(ContentType.JSON)
                .body(obj)
                .post(uriPath);
    }
}
