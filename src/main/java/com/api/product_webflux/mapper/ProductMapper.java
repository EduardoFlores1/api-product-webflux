package com.api.product_webflux.mapper;

import com.api.product_webflux.dto.CreateProductDTO;
import com.api.product_webflux.dto.ProductResponseDTO;
import com.api.product_webflux.dto.UpdateProductDTO;
import com.api.product_webflux.entity.ProductEntity;

public class ProductMapper {

    public static ProductResponseDTO fromEntityToResponse(ProductEntity productEntity) {
        return new ProductResponseDTO(
                productEntity.getProductId(),
                productEntity.getName(),
                productEntity.getPrice()
        );
    }

    public static ProductEntity fromCreateToEntity(CreateProductDTO createProductDTO) {
        return ProductEntity.createCTR(
                createProductDTO.Name(),
                createProductDTO.Price()
        );
    }

    public static ProductEntity fromUpdateToEntity(int productId, UpdateProductDTO updateProductDTO) {
        return ProductEntity.updateCTR(
                productId,
                updateProductDTO.Name(),
                updateProductDTO.Price()
        );
    }
}
