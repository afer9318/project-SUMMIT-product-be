package com.B2B.SP.product.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductNotFoundException(String message){
        super(message);
    }

    public ProductNotFoundException(Throwable cause){
        super(cause);
    }
}
