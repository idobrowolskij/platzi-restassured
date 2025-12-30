package de.id.platzi.tests.products;


import de.id.platzi.base.BaseTest;
import de.id.platzi.clients.AuthClient;
import de.id.platzi.clients.CategoryClient;
import de.id.platzi.clients.ProductClient;
import de.id.platzi.models.request.ProductRequest;
import de.id.platzi.models.response.Category;
import de.id.platzi.models.response.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest extends BaseTest {

    private AuthClient authClient;
    private CategoryClient categoryClient;
    private ProductClient productClient;

    @BeforeEach
    void setup() {
        this.authClient = new AuthClient(config);
        this.categoryClient = new CategoryClient(config);
        this.productClient = new ProductClient(config);
    }

    @Test
    void products_shouldReturnListOfProducts() {
        int limit = 10;

        List<Product> products = productClient.getProducts(limit, 0);

        assertFalse(products.isEmpty());
        assertTrue(products.size() <= limit);
    }

    @Test
    void getProductById_shouldReturnCreatedProduct() {
        List<Category> categories = categoryClient.getCategories(5);
        String u = UUID.randomUUID().toString().substring(0,8);
        ProductRequest productRequest = new ProductRequest(
          "Test-" + u, new BigDecimal(100), "Test Description", categories.get(0).id(), List.of("https://i.imgur.com/cSytoSD.jpeg")
        );
        Product created = productClient.createProduct(productRequest);
        Product fetched = productClient.getProductById(created.id());

        assertEquals(created.id(), fetched.id());
        assertEquals(productRequest.title(), fetched.title());
    }
}
