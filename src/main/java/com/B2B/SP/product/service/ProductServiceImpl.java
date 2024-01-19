package com.B2B.SP.product.service;

import com.B2B.SP.product.dao.ProductRepository;
import com.B2B.SP.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository theProductRepository){
        this.productRepository = theProductRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long productId) {
        Optional<Product> result = productRepository.findById(productId);

        Product theProduct = null;

        if (result.isPresent()){
            theProduct = result.get();
        }
        else {
            throw new RuntimeException("Did not find product id: " + productId);
        }

        return theProduct;
    }

    @Override
    public Product save(Product theProduct) {
        return productRepository.save(theProduct);
    }

    @Override
    public Product update(Product theProduct) {
        return productRepository.save(theProduct);
    }

    @Override
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Boolean softDeleteById(Long productId) {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            existingProduct.setActive(Boolean.FALSE);
            productRepository.save(existingProduct);
            return true;
        }else{
            return false;
        }
    }
}
