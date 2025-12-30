package de.id.platzi.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Profile(
        Long id,
        String email,
        String name,
        String role,
        String avatar
) {}
