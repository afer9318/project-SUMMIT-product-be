package com.B2B.SP.product.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productId;

    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be a positive number")
    private Long categoryId;

    @NotNull(message = "Brand ID cannot be null")
    @Positive(message = "Brand ID must be a positive number")
    private Long brandId;

    @NotNull(message = "Supplier ID cannot be null")
    @Positive(message = "Supplier ID must be a positive number")
    private Long supplierId;

    @NotNull(message = "Stock ID cannot be null")
    @Positive(message = "Stock ID must be a positive number")
    private Long stockId;

    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product price must be a positive number")
    private BigDecimal productPrice;

    private String productImage;

    @AssertTrue(message = "isAvailable must be either true or false")
    private Boolean isAvailable = Boolean.TRUE;

    @AssertTrue(message = "isActive must be either true or false")
    private Boolean isActive = Boolean.TRUE;
}
