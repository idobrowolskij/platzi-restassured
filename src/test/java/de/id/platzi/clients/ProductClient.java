package de.id.platzi.clients;

import de.id.platzi.config.Config;
import de.id.platzi.models.request.CreateProductRequest;
import de.id.platzi.models.request.UpdateProductRequest;
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

    public Response createProductRaw(CreateProductRequest product) {
        return req()
                .body(product)
                .post("/products");
    }

    public Product createProduct(CreateProductRequest product) {
        Response res = createProductRaw(product);
        checkStatusCode(201, res);
        return res.as(Product.class);
    }

    public Response updateProductRaw(UpdateProductRequest product, Long id) {
        return req()
                .body(product)
                .pathParam("id", id)
                .put("/products/{id}");
    }

    public Product updateProduct(UpdateProductRequest product, Long id) {
        Response res = updateProductRaw(product, id);
        checkStatusCode(200, res);
        return res.as(Product.class);
    }

    public Response deleteProductRaw(Long id) {
        return req()
                .pathParam("id", id)
                .delete("/products/{id}");
    }

    public Response deleteProduct(Long id) {
        Response res = deleteProductRaw(id);
        checkStatusCode(200, res);
        return res;
    }
}
