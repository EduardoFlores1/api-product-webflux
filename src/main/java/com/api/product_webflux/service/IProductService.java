package com.api.product_webflux.service;

import com.api.product_webflux.dto.CreateProductDTO;
import com.api.product_webflux.dto.ProductResponseDTO;
import com.api.product_webflux.dto.UpdateProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    Flux<ProductResponseDTO> getAll();
    Mono<ProductResponseDTO> getById(int productId);
    Mono<ProductResponseDTO> save(CreateProductDTO createProduct);
    Mono<ProductResponseDTO> update(int productId, UpdateProductDTO updateProduct);
    Mono<Void> deleteById(int productId);
}
