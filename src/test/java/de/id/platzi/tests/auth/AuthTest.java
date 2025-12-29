package de.id.platzi.tests.auth;

import de.id.platzi.base.BaseTest;
import de.id.platzi.clients.AuthClient;
import de.id.platzi.models.request.LoginRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthTest extends BaseTest {

    @Test
    void login_shouldNotReturnAccessAndRefreshTokenOnInvalidCredentials() {
        Response res = new AuthClient(config).loginRaw(new LoginRequest("test@test.com", "wrongpassword123"));

        assertEquals(401, res.statusCode(), "expected 401 but was " + res.statusCode());
        assertNull(res.jsonPath().getString("access_token"));
        assertNull(res.jsonPath().getString("refresh_token"));
    }
}
