package com.app.productservice.exceptionhandler;
import com.app.productservice.exceptions.ProductNotFoundException;
import com.app.productservice.exceptions.ProductNotFoundExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleGenericException(Exception e) {

        return new ResponseEntity<>(new ExceptionDTO(e.getMessage(), "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDTO> handleArithmeticException(ArithmeticException e) {

        return new ResponseEntity<>(new ExceptionDTO(e.getMessage(), "This is internal server error"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDTO> handleArithmeticException(ArrayIndexOutOfBoundsException e) {

        return new ResponseEntity<>(new ExceptionDTO(e.getMessage(), "Array index of bounds exception"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDTO> handleProductNotFoundException(ProductNotFoundException e) {

        ProductNotFoundExceptionDTO dto = new ProductNotFoundExceptionDTO();
        dto.setMessage( "Product with id "+e.getId()+" not found");
        return new ResponseEntity<>(dto , HttpStatus.NOT_FOUND);
    }
}
