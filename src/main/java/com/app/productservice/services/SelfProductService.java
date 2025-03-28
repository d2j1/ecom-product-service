package com.app.productservice.services;

import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.modals.Category;
import com.app.productservice.modals.Product;
import com.app.productservice.repository.CategoryRepository;
import com.app.productservice.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

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
        this.productRepository.deleteById(id);
    }

    @Override
    public Product replaceProduct(long id, Product product) throws ProductNotFoundException {

        Optional<Product> existingProductOptional = this.productRepository.findById(id);

        if( existingProductOptional.isPresent() ){

            Product existingProduct = existingProductOptional.get();

            existingProduct.setTitle(product.getTitle());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setCategory(product.getCategory());

            if(product.getCategory().getId() == null){
                Category category = product.getCategory();
                category = this.categoryRepository.save(category);
                existingProduct.setCategory(category);

            }



            return this.productRepository.save(existingProduct);

        }else{
            throw new ProductNotFoundException(id, "product not found");
        }


    }

    // Method to fetch products with pagination
    public Page<Product> getAllProductsPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(pageable);
    }
}
