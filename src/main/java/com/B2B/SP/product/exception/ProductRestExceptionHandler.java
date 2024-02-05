package com.B2B.SP.product.exception;

import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ProductRestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ProductRestExceptionHandler.class);

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ProductErrorResponse> handleProductNotFoundException(ProductNotFoundException exception){

        // create a ProductErrorResponse
        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        logger.error("ProductNotFoundException. Status: {}. Message: {}. Timestamp: {}",
                error.getStatus(),
                error.getMessage(),
                error.getTimestamp());

        // return responseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ProductErrorResponse> handleBadRequestException(Exception exception){

        // create a ProductErrorResponse
        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        logger.error("BadRequestException. Status: {}. Message: {}. Timestamp: {}",
                error.getStatus(),
                error.getMessage(),
                error.getTimestamp());

        // return responseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Validation Exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new ValidationErrorResponse(HttpStatus.BAD_REQUEST, "Validation errors", errors));
    }

    // Generic Exception
    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleProductServiceException(Exception exception){

        // create a ProductErrorResponse
        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        logger.error("ProductServiceException. Status: {}. Message: {}. Timestamp: {}",
                error.getStatus(),
                error.getMessage(),
                error.getTimestamp());

        // return responseEntity
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
