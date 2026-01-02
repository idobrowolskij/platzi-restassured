package de.id.platzi.clients;

import de.id.platzi.config.Config;
import de.id.platzi.models.request.ProductRequest;
import de.id.platzi.models.response.Product;
import io.restassured.response.Response;

import java.util.List;

public final class ProductClient extends BaseClient {

    public ProductClient(Config config) { super(config); }

    public Response getProductsRaw(int limit, int offset) {
        return req()
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .get("/products");
    }

    public List<Product> getProducts(int limit, int offset) {
        Response res = getProductsRaw(limit, offset);
        checkStatusCode(200, res);
        return res.jsonPath().getList(".", Product.class);
    }

    public Response getProductByIdRaw(Long id) {
        return req()
                .pathParam("id", id)
                .get("/products/{id}");
    }

    public Product getProductById(Long id) {
        Response res = getProductByIdRaw(id);
        checkStatusCode(200, res);
        return res.as(Product.class);
    }

    public Response createProductRaw(ProductRequest product) {
        return req()
                .body(product)
                .post("/products");
    }

    public Product createProduct(ProductRequest product) {
        Response res = createProductRaw(product);
        checkStatusCode(201, res);
        return res.as(Product.class);
    }

    public Response updateProductRaw(Product product) {
        return req()
                .body(product)
                .pathParam("id", product.id())
                .put("/products/{id}");
    }

    public Product updateProduct(Product product) {
        Response res = updateProductRaw(product);
        checkStatusCode(200, res);
        return res.as(Product.class);
    }

    public Response deleteProductRaw(Product product) {
        return req()
                .pathParam("id", product.id())
                .delete("/products/{id}");
    }

    public Response deleteProduct(Product product) {
        Response res = deleteProductRaw(product);
        checkStatusCode(200, res);
        return res;
    }
}
