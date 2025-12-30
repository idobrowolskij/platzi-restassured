package de.id.platzi.clients;

import de.id.platzi.config.Config;
import de.id.platzi.models.request.LoginRequest;
import de.id.platzi.models.request.RefreshTokenRequest;
import de.id.platzi.models.response.AuthToken;
import de.id.platzi.models.response.Profile;
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
            throw new AssertionError("Expected 201 but was " + code + ". Body: " + res.asString());
        }
        return res.as(AuthToken.class);
    }

    public AuthToken loginWithDefaultUser() {
        return login(new LoginRequest(config.email(), config.password()));
    }

    public Response getProfileRaw(AuthToken token) {
        return req()
                .header("Authorization", "Bearer " + token.accessToken())
                .get("/auth/profile");
    }

    public Profile getProfile(AuthToken token) {
        Response res = getProfileRaw(token);

        int code = res.statusCode();
        if (code != 200) {
            throw new AssertionError("Expected 200 but was " + code + ". Body: " + res.asString());
        }
        return res.as(Profile.class);
    }

    public Response getRefreshTokenRaw(RefreshTokenRequest refreshTokenRequest) {
        return req()
                .body(refreshTokenRequest)
                .post("/auth/refresh-token");
    }

    public AuthToken getRefreshToken(RefreshTokenRequest refreshTokenRequest) {
        Response res = getRefreshTokenRaw(refreshTokenRequest);

        int code = res.statusCode();
        if (code != 201) {
            throw new AssertionError("Expected 201 but was " + code + ". Body: " + res.asString());
        }
        return res.as(AuthToken.class);
    }
}
