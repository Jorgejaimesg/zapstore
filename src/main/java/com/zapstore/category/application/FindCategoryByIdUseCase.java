package com.zapstore.category.application;

import java.util.Optional;

import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;

public class FindCategoryByIdUseCase {
    private final CategoryService categoryService;
    public FindCategoryByIdUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Optional<Category> findCategoryById(int id) {
        return categoryService.findCategoryById(id);
    }
}
