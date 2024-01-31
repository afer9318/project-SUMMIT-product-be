package com.B2B.SP.product.service;

import com.B2B.SP.product.dao.ProductRepository;
import com.B2B.SP.product.entity.Product;
import com.B2B.SP.product.exception.ProductNotFoundException;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final EntityManager entityManager;

    public ProductServiceImpl(ProductRepository theProductRepository, EntityManager theEntityManager){
        this.productRepository = theProductRepository;
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Product> findAll() {
        // Enable the Hibernate filter
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("activeProductFilter");
        filter.setParameter("isProductActive", true);

        // Retrieve the list of products
        List<Product> productList = productRepository.findAll();

        // Disable the filter to avoid unwanted filtering in subsequent operations
        session.disableFilter("activeProductFilter");

        return productList;
    }

    @Override
    @Transactional
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
    }

    @Override
    @Transactional
    public Product save(Product theProduct) {
        return productRepository.save(theProduct);
    }

    @Override
    @Transactional
    public Product update(Product theProduct) {
        return productRepository.save(theProduct);
    }

    @Override
    @Transactional
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }
}
