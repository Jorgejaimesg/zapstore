package com.zapstore.category.domain.service;

import java.util.List;
import java.util.Optional;
import com.zapstore.category.domain.entity.Category;

public interface CategoryService {
    void createCategory(Category Category);
    void updateCategory(Category Category);
    Category deleteCategory(String name);
    Optional<Category> findCategoryByName (String name);
    Optional<Category> findCategoryById (int Id);
    List<Category> findAllCategory();
}
