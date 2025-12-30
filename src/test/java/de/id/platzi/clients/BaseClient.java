package de.id.platzi.clients;

import de.id.platzi.config.Config;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
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
}
