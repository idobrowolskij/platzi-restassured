package de.id.platzi.models.response;

import java.time.Instant;

public record Category(
        Long id,
        String name,
        String slug,
        String image,
        Instant creationAt,
        Instant updatedAt
) {}
