package com.app.productservice.services;

import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.modals.Category;
import com.app.productservice.modals.Product;
import com.app.productservice.repository.CategoryRepository;
import com.app.productservice.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getProduct(long id) throws ProductNotFoundException {

        Optional<Product> product =productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ProductNotFoundException(id, "Product Not Found");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(long id, Product product) {

        return null;
    }

    @Override
    public Product createProduct(Product product) {

        // here we have to first create category in the category table and then save the product in the table

        Category cat = product.getCategory();

        if( cat.getId() == null ){
            Category savedCat = categoryRepository.save(cat);
            product.setCategory(savedCat);
        }
        Product savedProduct = productRepository.save(product);
        Category fetchedCategory = categoryRepository.findById(savedProduct.getCategory().getId()).orElse(null);

        savedProduct.setCategory(fetchedCategory);

        return savedProduct;
    }

    @Override
    public void deleteProduct(long id) {

    }

    @Override
    public Product replaceProduct(long id, Product product) {
        return null;
    }
}
