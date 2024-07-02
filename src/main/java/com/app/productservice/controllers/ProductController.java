package com.app.productservice.controllers;

import com.app.productservice.modals.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")

public class ProductController {

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable("id") long id ){

        return null;

    }

}
