package com.zapstore.category.application;

import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;

public class UpdateCategoryUseCase {
    private final CategoryService categoryService;

    public UpdateCategoryUseCase(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    public void execute(Category category){
        categoryService.updateCategory(category);
    }
}
