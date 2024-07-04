package com.app.productservice.services;

import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.modals.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product updateProduct(long id, Product product);
}
