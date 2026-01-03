package de.id.platzi.tests.category;

import de.id.platzi.assertions.CategoryAsserts;
import de.id.platzi.base.BaseTest;
import de.id.platzi.clients.CategoryClient;
import de.id.platzi.models.request.CreateCategoryRequest;
import de.id.platzi.models.request.UpdateCategoryRequest;
import de.id.platzi.models.response.Category;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest extends BaseTest {

    private CategoryClient categoryClient;

    @BeforeEach
    void setup() {
        this.categoryClient = new CategoryClient(config);
    }

    @Test
    void category_shouldReturnListOfCategories() {
        int limit = 5;

        List<Category> categories = categoryClient.getCategories(limit);

        assertFalse(categories.isEmpty());
        CategoryAsserts.assertValidCategory(categories.get(0));
    }

    @Test
    void category_shouldReturnCategoryById() {
        List<Category> categories = categoryClient.getCategories(5);
        Long categoryId = categories.get(0).id();

        Category category = categoryClient.getCategoryById(categoryId);
        CategoryAsserts.assertValidCategory(category);
    }

    @Test
    void category_shouldCreateCategoryWithValidPayload() {
        String u = UUID.randomUUID().toString().substring(0, 8);
        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest(
                "Test" + u,
                "https://i.imgur.com/cSytoSD.jpeg"
        );

        Category created = categoryClient.createCategory(createCategoryRequest);

        CategoryAsserts.assertValidCategory(created);
    }

    @Test
    void category_shouldUpdateCreatedCategory() {
        String u = UUID.randomUUID().toString().substring(0, 8);
        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest(
                "Test" + u,
                "https://i.imgur.com/cSytoSD.jpeg"
        );
        Category created = categoryClient.createCategory(createCategoryRequest);
        CategoryAsserts.assertValidCategory(created);
        String newRandom = UUID.randomUUID().toString().substring(0, 8);
        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(
                "Test" + newRandom,
                "https://i.imgur.com/cSytoSD.jpeg"
        );

        Category updated = categoryClient.updateCategory(updateCategoryRequest, created.id());

        CategoryAsserts.assertValidCategory(updated);
        assertEquals(updateCategoryRequest.name(), updated.name());
    }

    @Test
    void category_shouldDeleteCreatedCategory() {
        String u = UUID.randomUUID().toString().substring(0, 8);
        CreateCategoryRequest createCategoryRequest = new CreateCategoryRequest(
                "Test" + u,
                "https://i.imgur.com/cSytoSD.jpeg"
        );
        Category created = categoryClient.createCategory(createCategoryRequest);
        CategoryAsserts.assertValidCategory(created);

        categoryClient.deleteCategoryById(created.id());
        Response queryCategory = categoryClient.getCategoryByIdRaw(created.id());

        assertEquals(400, queryCategory.statusCode());
    }
}
