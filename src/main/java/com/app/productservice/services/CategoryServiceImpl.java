package com.app.productservice.services;

import com.app.productservice.exceptions.CategoryNotFoundException;
import com.app.productservice.modals.Category;
import com.app.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements com.app.productservice.services.CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(long id) throws CategoryNotFoundException {
        Optional<Category> category = this.categoryRepository.findById(id);

        if(category.isPresent()) {
            return category.get();
        }else {
            throw new CategoryNotFoundException(id, "Category not found");
        }

    }

    @Override
    public Category saveCategory(Category category) {
    return this.categoryRepository.save(category);

    }

    @Override
    public Category updateCategory(long id, Category category) throws CategoryNotFoundException {
        Optional<Category> categoryOptional = this.categoryRepository.findById(id);

        if(categoryOptional.isPresent()) {
            Category categoryUpdated = categoryOptional.get();
            categoryUpdated.setTitle(category.getTitle());
           return this.categoryRepository.save(categoryUpdated);
        }else{
            throw new CategoryNotFoundException(id, "category not found");
        }
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
