package de.id.platzi.models.request;

public record LoginRequest(
        String email,
        String password
) {}
