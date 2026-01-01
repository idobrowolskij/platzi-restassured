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
        checkStatusCode(201, res);
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
        checkStatusCode(200, res);
        return res.as(Profile.class);
    }

    public Response getRefreshTokenRaw(RefreshTokenRequest refreshTokenRequest) {
        return req()
                .body(refreshTokenRequest)
                .post("/auth/refresh-token");
    }

    public AuthToken getRefreshToken(RefreshTokenRequest refreshTokenRequest) {
        Response res = getRefreshTokenRaw(refreshTokenRequest);
        checkStatusCode(201, res);
        return res.as(AuthToken.class);
    }
}
