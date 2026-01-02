package de.id.platzi.assertions;

import de.id.platzi.models.response.Product;

import static org.junit.jupiter.api.Assertions.*;

public final class ProductAsserts {
    private ProductAsserts() {}

    public static void assertValidProduct(Product product) {
        assertAll(
                () -> assertNotNull(product.id(), "id is null"),
                () -> assertNotNull(product.title(), "title is null"),
                () -> assertNotNull(product.slug(), "slug is null"),
                () -> assertNotNull(product.price(), "price is null"),
                () -> assertNotNull(product.description(), "description is null"),
                () -> assertNotNull(product.category(), "category is null"),
                () -> assertNotNull(product.images(), "images is null"),
                () -> assertNotNull(product.creationAt(), "creationAt is null")
        );
    }
}
