package com.api.product_webflux.mapper;

import com.api.product_webflux.dto.ProductResponseDTO;
import com.api.product_webflux.entity.ProductEntity;

public class ProductMapper {

    public static ProductResponseDTO fromEntityToResponse(ProductEntity productEntity) {
        return new ProductResponseDTO(
                productEntity.getProductId(),
                productEntity.getName(),
                productEntity.getPrice()
        );
    }
}
