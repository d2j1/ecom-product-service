package com.app.productservice.services;

import com.app.productservice.modals.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(long id);
    List<Product> getAllProducts();
}
