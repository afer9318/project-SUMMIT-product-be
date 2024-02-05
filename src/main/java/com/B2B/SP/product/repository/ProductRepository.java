package com.B2B.SP.product.repository;

import com.B2B.SP.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.productId = :productId AND p.isActive = true")
    Optional<Product> findByIdAndIsActive(@Param("productId") Long productId);
}
