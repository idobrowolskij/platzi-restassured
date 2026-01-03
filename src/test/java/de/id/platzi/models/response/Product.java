package de.id.platzi.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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
