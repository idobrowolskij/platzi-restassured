package de.id.platzi.clients;

import de.id.platzi.config.Config;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseClient {
    protected final Config config;
    private final RequestSpecification spec;

    protected BaseClient(Config config) {
        this.config = config;
        this.spec = new RequestSpecBuilder()
                .setBaseUri(config.baseUrl())
                .setContentType("application/json")
                .setAccept("application/json")
                .addFilter(new AllureRestAssured())
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected RequestSpecification req() {
        return RestAssured.given().spec(spec);
    }

    protected void checkStatusCode(int expected, Response res) {
        int code = res.statusCode();
        if (code != expected) {
            throw new AssertionError("Expected " + expected + " but was " + code + ". Body: " + res.asString());
        }
    }
}
