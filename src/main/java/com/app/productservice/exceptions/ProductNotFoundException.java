package com.app.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception{

    private long id;
    public ProductNotFoundException(long id, String message){
        super(message);
        this.id = id;

    }
}
