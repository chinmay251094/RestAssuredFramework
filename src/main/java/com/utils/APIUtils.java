package com.utils;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

public final class APIUtils {
    private APIUtils() {
    }

    @SneakyThrows
    public static String readJsonAndGetAsString(String filePath) {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    @SneakyThrows
    public static void storeDataAsJsonFile(String filepath, Response response) {
        Files.write(Paths.get(filepath), response.asByteArray());
    }
}
