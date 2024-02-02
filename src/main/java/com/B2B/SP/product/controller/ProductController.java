package com.B2B.SP.product.controller;

import com.B2B.SP.product.model.Product;
import com.B2B.SP.product.dto.ProductDto;
import com.B2B.SP.product.service.ProductService;
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
    public ProductDto getProduct(@PathVariable Long productId){
        return productService.findById(productId);
    }

    @PostMapping("/")
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
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
