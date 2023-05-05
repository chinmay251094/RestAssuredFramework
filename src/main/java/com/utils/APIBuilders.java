package com.utils;

import com.enums.PropertiesType;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.*;

public final class APIBuilders {
    private APIBuilders() {
    }
    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);
    public static RequestSpecification buildRequestForGetCalls() {
        return given().log().all()
                .baseUri(frameworkConfig.baseurl());
    }

    public static Response buildRequestForPostCalls(Object obj, String uriPath) {
        return given().log().all()
                .baseUri(frameworkConfig.baseurl())
                .contentType(ContentType.JSON)
                .body(obj)
                .post(uriPath);
    }
}
