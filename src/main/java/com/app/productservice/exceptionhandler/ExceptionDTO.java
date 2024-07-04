package com.app.productservice.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionDTO {
    // Getters and setters
    private String error;
    private String message;

    public ExceptionDTO(String error, String message) {
        this.error = error;
        this.message = message;
    }

}
