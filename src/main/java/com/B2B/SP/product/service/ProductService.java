package com.B2B.SP.product.service;

import com.B2B.SP.product.dto.ProductDto;
import com.B2B.SP.product.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    ProductDto findById(Long productId);

//    Product save(Product theProduct);
    ProductDto save(ProductDto productDto);

//    Product update(Product theProduct);

    ProductDto update(ProductDto productDto);

    void deleteById(Long productId);
}
