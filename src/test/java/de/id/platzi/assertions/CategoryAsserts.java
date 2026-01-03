package de.id.platzi.assertions;


import de.id.platzi.models.response.Category;

import static org.junit.jupiter.api.Assertions.*;

public final class CategoryAsserts {
    private CategoryAsserts() {}

    public static void assertValidCategory(Category category) {
        assertAll(
                () -> assertNotNull(category.id()),
                () -> assertNotNull(category.name()),
                () -> assertNotNull(category.slug()),
                () -> assertNotNull(category.image()),
                () -> assertNotNull(category.creationAt())
        );
    }
}
