package de.id.platzi.tests.smoke;

import de.id.platzi.assertions.AuthAsserts;
import de.id.platzi.base.BaseTest;
import de.id.platzi.clients.AuthClient;
import de.id.platzi.models.response.AuthToken;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("smoke")
public class AuthSmokeTest extends BaseTest {

    @Test
    void login_shouldReturnAccessAndRefreshToken() {
        AuthClient auth = new AuthClient(config);
        AuthToken token = auth.loginWithDefaultUser();
        AuthAsserts.assertValidTokens(token);
    }
}
