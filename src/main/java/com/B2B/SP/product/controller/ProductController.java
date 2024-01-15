package com.B2B.SP.product.controller;

import com.B2B.SP.product.entity.Product;
import com.B2B.SP.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService theProductService) {
        this.productService = theProductService;
    }

    @GetMapping("/product")
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable Long productId){
        return productService.findById(productId);
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product){
        return  productService.update(product);
    }

    @DeleteMapping("/product/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteById(productId);
    }


}
