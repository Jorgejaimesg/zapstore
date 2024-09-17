package com.zapstore.category.application;

import java.util.Optional;

import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;

public class FindCategoryByNameUseCase {
    private final CategoryService categoryService;

    public FindCategoryByNameUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Optional<Category> findCategoryByName(String categoryName) {
        return categoryService.findCategoryByName(categoryName);
    }
}
