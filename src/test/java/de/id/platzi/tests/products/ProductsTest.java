package de.id.platzi.tests.products;


import de.id.platzi.assertions.ProductAsserts;
import de.id.platzi.base.BaseTest;
import de.id.platzi.clients.CategoryClient;
import de.id.platzi.clients.ProductClient;
import de.id.platzi.models.request.ProductRequest;
import de.id.platzi.models.response.Category;
import de.id.platzi.models.response.Product;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest extends BaseTest {

    private CategoryClient categoryClient;
    private ProductClient productClient;

    @BeforeEach
    void setup() {
        this.categoryClient = new CategoryClient(config);
        this.productClient = new ProductClient(config);
    }

    @Test
    void products_shouldReturnListOfProducts() {
        int limit = 10;

        List<Product> products = productClient.getProducts(limit, 0);

        assertFalse(products.isEmpty());
        ProductAsserts.assertValidProduct(products.get(0));
        assertTrue(products.size() <= limit);
    }

    @Test
    void getProductById_shouldReturnCreatedProduct() {
        List<Category> categories = categoryClient.getCategories(5);
        String u = UUID.randomUUID().toString().substring(0,8);
        ProductRequest productRequest = new ProductRequest(
          "Test-" + u,
                new BigDecimal(100),
                "Test Description",
                categories.get(0).id(),
                List.of("https://i.imgur.com/cSytoSD.jpeg")
        );
        Product created = productClient.createProduct(productRequest);
        Product fetched = productClient.getProductById(created.id());

        assertEquals(created.id(), fetched.id());
        assertEquals(productRequest.title(), fetched.title());
        ProductAsserts.assertValidProduct(fetched);
    }

    @Test
    void product_shouldNotBeCreatedWithInvalidPayload() {
        String u = UUID.randomUUID().toString().substring(0,8);
        // invalid category, empty images urls
        ProductRequest productRequest = new ProductRequest(
                "Test-" + u,
                new BigDecimal(50),
                "Test",
                50000L,
                List.of("")
        );
        Response res = productClient.createProductRaw(productRequest);
        assertEquals(400, res.statusCode());
    }

    @Test
    void getProductById_shouldReturn400_whenProductNotFound() {
        Response res = productClient.getProductByIdRaw(999999L);
        assertEquals(400, res.statusCode());
    }

    @Test
    void product_shouldUpdateTitle() {
        Long categoryId = categoryClient.getCategories(5).get(0).id();
        String u = UUID.randomUUID().toString().substring(0,8);

        Product created = productClient.createProduct(new ProductRequest(
                "Test-" + u,
                new BigDecimal(100),
                "Test",
                categoryId,
                List.of("https://i.imgur.com/cSytoSD.jpeg")));

        String newTitle = "Updated-" + u;
        Product updated = productClient.updateProduct(new Product(
                created.id(),
                newTitle,
                created.slug(),
                created.price(),
                created.description(),
                created.category(),
                created.images(),
                created.creationAt(),
                created.updatedAt()));

        assertEquals(created.id(), updated.id());
        assertEquals(newTitle, updated.title());
        ProductAsserts.assertValidProduct(updated);
    }

    @Test
    void product_shouldDeleteCreatedProduct() {
        Long categoryId = categoryClient.getCategories(5).get(0).id();
        String u = UUID.randomUUID().toString().substring(0, 8);

        Product created = productClient.createProduct(new ProductRequest(
                "Test" + u,
                new BigDecimal(100),
                "Test", categoryId,
                List.of("https://i.imgur.com/cSytoSD.jpeg")
        ));

        Response del = productClient.deleteProductRaw(created);
        assertEquals(200, del.statusCode());

        Response get = productClient.getProductByIdRaw(created.id());
        assertEquals(400, get.statusCode());
    }
}
