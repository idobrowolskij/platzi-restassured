package de.id.platzi.models.request;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(
        String title,
        BigDecimal price,
        String description,
        Long categoryId,
        List<String> images
) {}
