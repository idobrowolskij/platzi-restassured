package de.id.platzi.clients;

import de.id.platzi.config.Config;
import de.id.platzi.models.response.Category;
import io.restassured.response.Response;

import java.util.List;

public class CategoryClient extends BaseClient {
    public CategoryClient(Config config) { super(config); }

    public Response getCategoriesRaw(int limit) {
        return req()
                .queryParam("limit", limit)
                .get("/categories");
    }

    public List<Category> getCategories(int limit) {
        Response res = getCategoriesRaw(limit);

        int code = res.statusCode();
        if (code != 200) {
            throw new AssertionError("Expected 200 but was " + code + ". Body: " + res.asString());
        }
        return res.jsonPath().getList(".", Category.class);
    }
}
