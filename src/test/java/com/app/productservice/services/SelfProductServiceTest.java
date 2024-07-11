package com.app.productservice.services;

import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.modals.Category;
import com.app.productservice.modals.Product;
import com.app.productservice.repository.CategoryRepository;
import com.app.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SelfProductServiceTest {



    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private SelfProductService selfProductService;


    @Test
    void getProduct() throws ProductNotFoundException {

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");

        when( productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product expectedProduct = selfProductService.getProduct(1L);

        assertNotNull(expectedProduct);
        assertEquals(expectedProduct.getTitle(), product.getTitle());
        assertEquals(expectedProduct.getId(), product.getId());
        assertEquals(product, expectedProduct );

        verify(productRepository, times(1)).findById(1L);


        when( productRepository.findById(-1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> selfProductService.getProduct(-1L));


    }

    @Test
    void getAllProducts() {

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Test Product2");
        Product product3 = new Product();
        product3.setId(3L);
        product3.setTitle("Test Product3");
        Product product4 = new Product();
        product4.setId(4L);
        product4.setTitle("Test Product4");

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> expectedProducts = selfProductService.getAllProducts();
        assertNotNull(expectedProducts);
        assertEquals(expectedProducts.size(), products.size());
        assertEquals(expectedProducts.get(0), products.get(0));
        assertEquals(expectedProducts.get(1).getTitle(), products.get(1).getTitle());

        verify(productRepository, times(1)).findAll();

    }

    @Test
    void createProduct() {

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Test Category");

        product.setCategory(category);

        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));


        when(productRepository.save(product)).thenReturn(product);



        Product expectedProduct = selfProductService.createProduct(product);

        assertNotNull(expectedProduct);
        assertEquals(expectedProduct.getTitle(), product.getTitle());
        assertEquals(expectedProduct.getId(), product.getId());
        assertEquals(product, expectedProduct );
        verify(productRepository, times(1)).save(product);

        verify(categoryRepository, times(1)).findById(1L);


    }

    @Test
    void deleteProduct() {

        this.selfProductService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);


    }

    @Test
    void replaceProduct() throws ProductNotFoundException {

//
//        Category category = new Category();
//        category.setId(1L);
//        category.setTitle("Test Category");
//
//        Product product = new Product();
//        product.setId(1L);
//        product.setTitle("old Test Product");
//        product.setCategory(category);
//
//        Product newProduct = new Product();
//        newProduct.setId(1L);
//        newProduct.setTitle("new Test Product");
//        newProduct.setCategory(category);
//
//
//        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
//        when(categoryRepository.save(category)).thenReturn(category);
//        when(productRepository.save(newProduct)).thenReturn(newProduct);
//
//
//        Product replacedProduct = selfProductService.replaceProduct(1L,newProduct);
//
//        assertNotNull(replacedProduct);
//        assertNotNull(replacedProduct.getId());
//        assertNotNull(replacedProduct.getCategory());
//
//        assertEquals(replacedProduct.getTitle(), newProduct.getTitle());
//        assertEquals(replacedProduct.getId(), newProduct.getId());
//        assertEquals(newProduct, replacedProduct );
//        verify(productRepository, times(1)).save(newProduct);
//        verify(productRepository, times(1)).findById(1L);

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Test Category");

        Product product = new Product();
        product.setId(1L);
        product.setTitle("Old Test Product");
        product.setCategory(category);

        Product newProduct = new Product();
        newProduct.setId(1L);
        newProduct.setTitle("New Test Product");
        newProduct.setCategory(category);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.save(category)).thenReturn(category);
        when(productRepository.save(any(Product.class))).thenReturn(newProduct);

        Product replacedProduct = selfProductService.replaceProduct(1L, newProduct);

        assertNotNull(replacedProduct);
        assertNotNull(replacedProduct.getId());
        assertNotNull(replacedProduct.getCategory());

        assertEquals(replacedProduct.getTitle(), newProduct.getTitle());
        assertEquals(replacedProduct.getId(), newProduct.getId());
        assertEquals(newProduct, replacedProduct);

        verify(productRepository, times(1)).save(any(Product.class));
        verify(productRepository, times(1)).findById(1L);

    }
}