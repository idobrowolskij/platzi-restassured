package de.id.platzi.tests.auth;

import de.id.platzi.assertions.AuthAsserts;
import de.id.platzi.base.BaseTest;
import de.id.platzi.clients.AuthClient;
import de.id.platzi.models.request.LoginRequest;
import de.id.platzi.models.request.RefreshTokenRequest;
import de.id.platzi.models.response.AuthToken;
import de.id.platzi.models.response.Profile;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthTest extends BaseTest {

    private AuthClient authClient;

    @BeforeEach
    void setup() {
        authClient = new AuthClient(config);
    }

    @Test
    void login_shouldNotReturnAccessAndRefreshTokenOnInvalidCredentials() {
        Response res = authClient.loginRaw(new LoginRequest("test@test.com", "wrongpassword123"));

        assertEquals(401, res.statusCode(), "Expected 401 but was " + res.statusCode());
        assertNull(res.jsonPath().getString("access_token"));
        assertNull(res.jsonPath().getString("refresh_token"));
    }

    @Test
    void profile_shouldReturnProfileOnValidTokenAfterLogin() {
        AuthToken token = authClient.loginWithDefaultUser();
        Profile profile = authClient.getProfile(token);
        AuthAsserts.assertValidProfile(profile);
    }

    @Test
    void profile_shouldNotReturnProfileOnInvalidToken() {
        Response res = authClient.getProfileRaw(new AuthToken("123wrongtoken", "123wrongrefreshtoken"));
        assertEquals(401, res.statusCode(), "Expected 401 but was " + res.statusCode());
    }

    @Test
    void refreshToken_shouldReturnNewTokensOnValidRefreshToken() {
        AuthToken oldToken = authClient.loginWithDefaultUser();
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(oldToken.refreshToken());
        AuthToken newToken = authClient.getRefreshToken(refreshTokenRequest);
        AuthAsserts.assertValidTokens(newToken);
        assertNotEquals(oldToken.accessToken(), newToken.accessToken());
    }

    @Test
    void refreshToken_shouldNotReturnNewTokensOnInvalidRefreshToken() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest("123invalidtoken");
        Response res = authClient.getRefreshTokenRaw(refreshTokenRequest);
        assertEquals(401, res.statusCode(), "Expected 401 but was " + res.statusCode());
    }

}
