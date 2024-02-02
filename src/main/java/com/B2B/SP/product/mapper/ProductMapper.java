package com.B2B.SP.product.mapper;

import com.B2B.SP.product.dto.ProductDto;
import com.B2B.SP.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToDTo(Product product);
    Product dtoToProduct(ProductDto productDto);
}
