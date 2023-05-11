package com.utils;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public final class APIBuilders {
    private APIBuilders() {
    }
    private static final FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);
    public static RequestSpecification buildRequestForGetCalls() {
        return given().log().all()
                .baseUri(frameworkConfig.baseurl());
    }

    public static RequestSpecification buildRequestForPostCalls() {
        return buildRequestForGetCalls().contentType(ContentType.JSON);
    }
}
