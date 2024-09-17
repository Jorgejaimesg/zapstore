package com.zapstore.category.application;

import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;

public class CreateCategoryUseCase {
    private final CategoryService categoryService;

    public CreateCategoryUseCase(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    public void execute(Category Category){
        categoryService.createCategory(Category);
    }
}
