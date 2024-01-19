package com.B2B.SP.product.service;

import com.B2B.SP.product.dao.ProductRepository;
import com.B2B.SP.product.entity.Product;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

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
}
