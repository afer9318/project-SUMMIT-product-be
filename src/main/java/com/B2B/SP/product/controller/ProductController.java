package com.B2B.SP.product.controller;

import com.B2B.SP.product.model.Product;
import com.B2B.SP.product.dto.ProductDto;
import com.B2B.SP.product.service.ProductService;
import org.springframework.http.ResponseEntity;
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
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId){
        ProductDto productDto = productService.findById(productId);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping("/")
    public ProductDto saveProduct(@RequestBody ProductDto productDto){
        return productService.save(productDto);
    }

    @PutMapping("/")
    public Product updateProduct(@RequestBody Product product){
        return  productService.update(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteById(productId);
    }

}
