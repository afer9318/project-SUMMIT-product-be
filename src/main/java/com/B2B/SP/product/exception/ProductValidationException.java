package com.B2B.SP.product.exception;

public class ProductValidationException extends RuntimeException{
    public ProductValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductValidationException(String message){
        super(message);
    }

    public ProductValidationException(Throwable cause){
        super(cause);
    }
}
