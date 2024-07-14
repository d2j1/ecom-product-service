package com.app.productservice.services;

import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.modals.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getProduct(long id) throws ProductNotFoundException;
    List<Product> getAllProducts();


     Product createProduct(Product product);
     void deleteProduct(long id);
     Product replaceProduct(long id, Product product) throws ProductNotFoundException;


    Page<Product> getAllProductsPagination(int pageNo, int pageSize);
}
