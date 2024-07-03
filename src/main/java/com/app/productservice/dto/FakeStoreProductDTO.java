package com.app.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FakeStoreProductDTO {
    private long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

}
