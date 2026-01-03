package de.id.platzi.clients;

import de.id.platzi.config.Config;
import de.id.platzi.models.request.CreateCategoryRequest;
import de.id.platzi.models.request.UpdateCategoryRequest;
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
        checkStatusCode(200, res);
        return res.jsonPath().getList(".", Category.class);
    }

    public Response getCategoryByIdRaw(Long id) {
        return req()
                .pathParam("id", id)
                .get("/categories/{id}");
    }

    public Category getCategoryById(Long id) {
        Response res = getCategoryByIdRaw(id);
        checkStatusCode(200, res);
        return res.as(Category.class);
    }

    public Response createCategoryRaw(CreateCategoryRequest category) {
        return req()
                .body(category)
                .post("/categories");
    }

    public Category createCategory(CreateCategoryRequest category) {
        Response res = createCategoryRaw(category);
        checkStatusCode(201, res);
        return res.as(Category.class);
    }

    public Response updateCategoryRaw(UpdateCategoryRequest category, Long id) {
        return req()
                .body(category)
                .pathParam("id", id)
                .put("/categories/{id}");
    }

    public Category updateCategory(UpdateCategoryRequest category, Long id) {
        Response res = updateCategoryRaw(category, id);
        checkStatusCode(200, res);
        return res.as(Category.class);
    }

    public Response deleteCategoryByIdRaw(Long id) {
        return req()
                .pathParam("id", id)
                .delete("/categories/{id}");
    }

    public Response deleteCategoryById(Long id) {
        Response res = deleteCategoryByIdRaw(id);
        checkStatusCode(200, res);
        return res;
    }
}
