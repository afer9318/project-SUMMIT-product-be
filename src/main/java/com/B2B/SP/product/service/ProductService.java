package com.B2B.SP.product.service;

import com.B2B.SP.product.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long productId);

    Product save(Product theProduct);

    Product update(Product theProduct);

    void deleteById(Long productId);
}
