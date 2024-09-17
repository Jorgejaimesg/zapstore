package com.zapstore.category.application;

import java.util.List;

import com.zapstore.category.domain.entity.Category;
import com.zapstore.category.domain.service.CategoryService;

public class FindAllCategoryUseCase {
    private final CategoryService categoryService;

    public FindAllCategoryUseCase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public List<Category> findAllCategory() {
        return categoryService.findAllCategory();
    }
}
