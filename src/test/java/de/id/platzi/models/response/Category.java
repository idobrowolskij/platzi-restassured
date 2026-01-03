package de.id.platzi.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Category(
        Long id,
        String name,
        String slug,
        String image,
        Instant creationAt,
        Instant updatedAt
) {}
