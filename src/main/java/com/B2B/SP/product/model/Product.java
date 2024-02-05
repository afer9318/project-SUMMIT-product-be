package com.B2B.SP.product.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET is_active = false WHERE product_id=?")
@FilterDef(name = "activeProductFilter", parameters = @ParamDef(name = "isProductActive", type = Boolean.class))
@Filter(name = "activeProductFilter", condition = "is_active = :isProductActive")
public class Product {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_category_id")
    private Long categoryId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "is_available")
    private Boolean isAvailable = Boolean.TRUE;

    @Column(name = "is_active")
    private Boolean isActive = Boolean.TRUE;

}