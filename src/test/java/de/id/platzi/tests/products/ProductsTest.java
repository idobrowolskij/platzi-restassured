package de.id.platzi.tests.products;


import de.id.platzi.base.BaseTest;
import de.id.platzi.clients.AuthClient;
import de.id.platzi.clients.ProductClient;
import de.id.platzi.models.response.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest extends BaseTest {

    private AuthClient authClient;
    private ProductClient productClient;

    @BeforeEach
    void setup() {
        this.authClient = new AuthClient(config);
        this.productClient = new ProductClient(config);
    }

    @Test
    void products_shouldReturnListOfProducts() {
        int limit = 10;

        List<Product> products = productClient.getProducts(limit, 0);

        assertFalse(products.isEmpty());
        assertTrue(products.size() <= limit);
    }
}
