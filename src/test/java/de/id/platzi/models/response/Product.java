package de.id.platzi.models.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record Product(
   Long id,
   String title,
   String slug,
   BigDecimal price,
   String description,
   Category category,
   List<String> images,
   Instant creationAt,
   Instant updatedAt
) {}
