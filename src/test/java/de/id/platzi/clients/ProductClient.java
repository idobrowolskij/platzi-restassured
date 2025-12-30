package de.id.platzi.clients;

import de.id.platzi.config.Config;
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

        int code = res.statusCode();
        if (code != 200) {
            throw new AssertionError("Expected 200 but was " + code + ". Body: " + res.asString());
        }
        return res.jsonPath().getList(".", Product.class);
    }
}
