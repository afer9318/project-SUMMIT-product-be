package com.B2B.SP.product.service;

import com.B2B.SP.product.dto.ProductDto;
import com.B2B.SP.product.exception.BadRequestException;
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

    private final EntityManager entityManager;

    public ProductServiceImpl(ProductRepository theProductRepository, EntityManager theEntityManager){
        this.productRepository = theProductRepository;
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        try {
            logger.info("Finding all products");

            // Enable the Hibernate filter
            Session session = entityManager.unwrap(Session.class);
            Filter filter = session.enableFilter("activeProductFilter");
            filter.setParameter("isProductActive", true);

            // Retrieve the list of products
            List<Product> productList = productRepository.findAll();

            // Disable the filter to avoid unwanted filtering in subsequent operations
            session.disableFilter("activeProductFilter");

            return productList;
        }catch (Exception e){
            logger.error("Exception while finding all products", e);
            throw e;
        }
    }

    // Filter not working for findById()
    // https://stackoverflow.com/questions/62513505/hibernate-filters-are-not-working-for-apis-returning-single-result
    // https://stackoverflow.com/questions/45169783/hibernate-filter-is-not-applied-for-findone-crud-operation
    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long productId) {
      try {
          logger.info("Finding product by id: {}", productId);
          Product product = productRepository.findById(productId)
                  .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

          return ProductMapper.INSTANCE.productToDTo(product);
      }catch (Exception e){
          logger.error("Exception while finding product by id: {}", productId, e);
          throw e;
      }
    }

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        try {
            if (productDto.getProductId() != null){
                throw new BadRequestException("Saving product does not need an ID");
            }
            logger.info("Saving product: {}", productDto);
            Product product = ProductMapper.INSTANCE.dtoToProductSave(productDto);
            Product savedProduct = productRepository.save(product);

            return ProductMapper.INSTANCE.productToDTo(savedProduct);
        }catch (Exception e){
            logger.error("Exception while saving product", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto) {
        try{
            Long productId = productDto.getProductId();

            if (!productDto.getIsActive()){
                logger.error("Cannot update Product isActive as false");
                throw new BadRequestException("Cannot update Product isActive as false");
            }

            logger.info("Updating product: {}", productDto);

            Optional<Product> optionalProduct = productRepository.findByIdAndIsActive(productId);

            if (optionalProduct.isEmpty()){
                logger.error("Cannot update deleted product");
                throw new BadRequestException("Cannot update deleted product");
            }

            Product product = optionalProduct.get();
            Product updatedProduct = ProductMapper.INSTANCE.dtoToProduct(productDto);
            updatedProduct = productRepository.save(updatedProduct);
            logger.info("Updated product: {}", updatedProduct);
            return ProductMapper.INSTANCE.productToDTo(updatedProduct);
        }catch (Exception e){
            logger.error("Exception while updating product", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteById(Long productId) {
        try{
            logger.info("Deleting product: {}", productId);

            Optional<Product> optionalProduct = productRepository.findByIdAndIsActive(productId);

            if (optionalProduct.isEmpty()){
                logger.error("Product already deleted");
                throw new ProductNotFoundException("Product already deleted");
            }

            productRepository.deleteById(productId);
        }catch (Exception e){
            logger.error("Exception while deleting product", e);
            throw e;
        }
    }
}
