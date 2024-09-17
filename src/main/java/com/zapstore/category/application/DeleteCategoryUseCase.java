package com.zapstore.category.application;

import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;

public class DeleteCategoryUseCase {
    private final CategoryService categoryService;

    public DeleteCategoryUseCase (CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Category execute(String Name) {
        return categoryService.deleteCategory(Name);
    }
}
