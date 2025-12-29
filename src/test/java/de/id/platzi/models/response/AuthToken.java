package de.id.platzi.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthToken(
    @JsonProperty("access_token") String accessToken,
    @JsonProperty("refresh_token") String refreshToken
) { }
