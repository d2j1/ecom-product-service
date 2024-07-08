package com.app.productservice.services;

import com.app.productservice.exceptions.CategoryNotFoundException;
import com.app.productservice.modals.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategories();
    public Category getCategoryById(long id) throws CategoryNotFoundException;
    public Category saveCategory(Category category);
    public Category updateCategory(long id, Category category) throws CategoryNotFoundException;
    public void deleteCategory(long id);

}
