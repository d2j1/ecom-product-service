package com.app.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryNotFoundException extends Exception{

    private long id;

    public CategoryNotFoundException(long id, String message){
        super(message);
        this.id = id;

    }
}