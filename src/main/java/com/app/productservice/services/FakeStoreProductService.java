package com.app.productservice.services;

import com.app.productservice.dto.FakeStoreProductDTO;
import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.modals.Category;
import com.app.productservice.modals.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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
    public Product getProduct(long id) throws ProductNotFoundException {
        

        if (id < 0) {
            throw new ArithmeticException("Negative ID");
        }

            FakeStoreProductDTO dto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDTO.class);

        if(dto == null)
            throw new ProductNotFoundException(id, "product not found");


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

    @Override
    public Product updateProduct(long id, Product product) {

        FakeStoreProductDTO dto  = new FakeStoreProductDTO();

        dto.setId(product.getId());
        dto.setDescription(product.getDescription());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setImage(product.getImage());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
    return getProductFromFakeProductDto(response);
    }
}
