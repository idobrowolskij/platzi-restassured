package de.id.platzi.assertions;

import de.id.platzi.models.response.AuthToken;
import static org.junit.jupiter.api.Assertions.*;

public final class AuthAsserts {
    private AuthAsserts() {}

    public static void assertValidTokens(AuthToken t) {
        assertAll(
                () -> assertNotNull(t.accessToken(), "access_token is null"),
                () -> assertFalse(t.accessToken().isBlank(), "access_token is blank"),
                () -> assertNotNull(t.refreshToken(), "refresh_token is null"),
                () -> assertFalse(t.refreshToken().isBlank(), "refresh_token is blank")
        );
    }
}
