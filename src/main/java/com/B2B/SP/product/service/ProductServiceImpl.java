package com.B2B.SP.product.service;

import com.B2B.SP.product.dto.ProductDto;
import com.B2B.SP.product.exception.ProductNotFoundException;
import com.B2B.SP.product.mapper.ProductMapper;
import com.B2B.SP.product.repository.ProductRepository;
import com.B2B.SP.product.model.Product;
import jakarta.persistence.EntityManager;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private  final ProductMapper productMapper;

    private final EntityManager entityManager;

    public ProductServiceImpl(ProductRepository theProductRepository, ProductMapper productMapper, EntityManager theEntityManager){
        this.productRepository = theProductRepository;
        this.productMapper = productMapper;
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

//    @Override
//    @Transactional
//    public Product findById(Long productId) {
//        return productRepository.findById(productId)
//                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
//    }

//    @Override
//    @Transactional
//    public ProductDto findById(Long productId) {
//        Optional<Product> optionalProduct = productRepository.findById(productId);
//
//        if (optionalProduct.isPresent()){
//            ProductDto productDto = productMapper.productToDTo(optionalProduct.get());
//            System.out.println("ProductDTo: " + productDto.toString());
//            return productDto;
//        }
//        else {
//            throw new ProductNotFoundException("Product not found with id: " + productId);
//        }
//    }

    @Override
    @Transactional
    public ProductDto findById(Long productId) {
        logger.info("Finding product by id: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        ProductDto productDto = productMapper.productToDTo(product);

        logger.info("Product info: {}", productDto);

        return productDto;
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
