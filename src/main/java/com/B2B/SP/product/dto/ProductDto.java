package com.B2B.SP.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private Long categoryId;
    private Long brandId;
    private Long supplierId;
    private Long stockId;
    private BigDecimal productPrice;
    private String productImage;
    private Boolean isActive = Boolean.TRUE;
}
