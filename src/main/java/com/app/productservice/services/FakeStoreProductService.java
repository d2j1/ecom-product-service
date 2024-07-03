package com.app.productservice.services;

import com.app.productservice.dto.FakeStoreProductDTO;
import com.app.productservice.modals.Category;
import com.app.productservice.modals.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private final RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Product getProductFromFakeProductDto(FakeStoreProductDTO fakeStoreProductDTO){

        if(fakeStoreProductDTO == null){
            return null;
        }

        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImage(fakeStoreProductDTO.getImage());

        Category category = new Category();
        category.setDescription(fakeStoreProductDTO.getCategory());
        product.setCategory(category);


        return product;
    }
    @Override
    public Product getProduct(long id) {
        FakeStoreProductDTO dto =  restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDTO.class);


        return getProductFromFakeProductDto(dto);
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDTO[] dos =  restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);

        if(dos == null){return null;}
        List<Product> products = new ArrayList<>();
        for( FakeStoreProductDTO dto : dos ){
            products.add(getProductFromFakeProductDto(dto));
        }
        return products;
    }
}
