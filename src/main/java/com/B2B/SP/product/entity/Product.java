package com.B2B.SP.product.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;

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
    private  Long stockId;

    @Column(name = "product_price")
    private Long productPrice;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @Column(name = "is_active")
    private Boolean isActive = Boolean.TRUE;


    // constructors
    public Product(){

    }

    public Product(String productName, Long categoryId, Long brandId, Long supplierId, Long stockId, Long productPrice, String productImage, Boolean isAvailable, Boolean isActive) {
        this.productName = productName;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.supplierId = supplierId;
        this.stockId = stockId;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.isAvailable = isAvailable;
        this.isActive = isActive;
    }

    // getters/setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


    // toString
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", supplierId=" + supplierId +
                ", stockId=" + stockId +
                ", productPrice=" + productPrice +
                ", productImage='" + productImage + '\'' +
                ", isAvailable=" + isAvailable +
                ", isActive=" + isActive +
                '}';
    }

}
