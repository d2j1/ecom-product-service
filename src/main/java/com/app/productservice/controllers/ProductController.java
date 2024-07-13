package com.app.productservice.controllers;

import com.app.productservice.commons.AuthCommons;
import com.app.productservice.dto.UserDto;
import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.modals.Product;
import com.app.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Component
public class ProductController {

    private final ProductService productService;
    private AuthCommons authCommons;


    public ProductController(ProductService productService,AuthCommons authCommons ){
        this.productService = productService;
        this.authCommons = authCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id, @RequestHeader("authToken") String token ) throws ProductNotFoundException {

//     call user service validate token api to validate the token
        UserDto userDto = authCommons.validateToken(token);

        if( userDto == null ){
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
        }
        Product product = productService.getProduct(id);
//        if(product == null){
//           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);;
//        }
            return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){


        List<Product> products = productService.getAllProducts();
//        if(products == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);;
//        }
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable long id, @RequestBody Product product) throws ProductNotFoundException {

        Product rProduct =productService.replaceProduct(id, product);

        if(rProduct == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(rProduct, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct( @RequestBody Product product){

        Product rProduct =productService.createProduct(product);

        if(rProduct == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(rProduct, HttpStatus.CREATED);
    }

    // delete product by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
