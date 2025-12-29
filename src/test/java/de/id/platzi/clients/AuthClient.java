package de.id.platzi.clients;

import de.id.platzi.config.Config;
import de.id.platzi.models.request.LoginRequest;
import de.id.platzi.models.response.AuthToken;
import io.restassured.response.Response;



public final class AuthClient extends BaseClient {

    public AuthClient(Config config) {
        super(config);
    }

    public Response loginRaw(LoginRequest request) {
        return req()
                .body(request)
                .post("/auth/login");
    }

    public AuthToken login(LoginRequest request) {
        Response res = loginRaw(request);
        int code = res.statusCode();
        if (code != 201) {
            throw new AssertionError("Expected 201 but was " + code);
        }
        return res.as(AuthToken.class);
    }

    public AuthToken loginWithDefaultUser() {
        return login(new LoginRequest(config.email(), config.password()));
    }
}
