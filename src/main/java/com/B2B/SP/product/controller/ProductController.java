package com.B2B.SP.product.controller;

import com.B2B.SP.product.model.Product;
import com.B2B.SP.product.dto.ProductDto;
import com.B2B.SP.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService theProductService) {
        this.productService = theProductService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> findAllProducts(){
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId){
        ProductDto productDto = productService.findById(productId);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping("/")
    public ResponseEntity<ProductDto> saveProduct(@Validated @RequestBody ProductDto productDto){
        ProductDto savedProductDto = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDto);
    }

    @PutMapping("/")
    public ResponseEntity<ProductDto> updateProduct(@Validated @RequestBody ProductDto productDto){
        ProductDto updatedProductDto = productService.update(productDto);
        return  ResponseEntity.ok(updatedProductDto);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        productService.deleteById(productId);
        return ResponseEntity.ok("Product deleted successfully.");
    }

}
