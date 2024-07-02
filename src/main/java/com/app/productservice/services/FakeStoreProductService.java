package com.app.productservice.services;

import com.app.productservice.modals.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProduct(long id) {
        return restTemplate.getForObject("https://fakestoreapi.com/products/"+id, Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
