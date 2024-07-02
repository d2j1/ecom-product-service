package com.app.productservice.controllers;

import com.app.productservice.modals.Product;
import com.app.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable("id") long id ){

        return productService.getProduct(id);

    }

    @GetMapping
    public List<Product> getAllProducts(){

        return productService.getAllProducts();

    }



}
