package com.app.productservice.controllers;

import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.modals.Product;
import com.app.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService; // this object will be mocked


    @Test
    void getProductById() throws ProductNotFoundException {

        Product product1 = new Product();
        product1.setId(1L);

        when(productService.getProduct(1L)).thenReturn(product1);
        Product actualObject = productController.getProductById(1L).getBody(); // here getBody is helping us to get the body from the response entity

        assertNotNull(actualObject);

        assertEquals(product1, actualObject);
    }

    @Test
    void getAllProducts() {


    }

    @Test
    void replaceProduct() {
    }

    @Test
    void createProduct() {
    }
}