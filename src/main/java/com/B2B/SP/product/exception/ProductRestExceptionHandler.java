package com.B2B.SP.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductRestExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ProductErrorResponse> handleProductNotFoundException(ProductNotFoundException exception){

        // create a ProductErrorResponse
        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        // return responseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleBadRequestException(Exception exception){

        // create a ProductErrorResponse
        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        // return responseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
