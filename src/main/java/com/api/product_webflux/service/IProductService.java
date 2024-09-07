package com.api.product_webflux.service;

import com.api.product_webflux.dto.ProductRequestDTO;
import com.api.product_webflux.dto.ProductResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {
    Flux<ProductResponseDTO> getAll();
    Mono<ProductResponseDTO> getById(int productId);
    Mono<ProductResponseDTO> save(ProductRequestDTO createProduct);
    Mono<ProductResponseDTO> update(int productId, ProductRequestDTO updateProduct);
    Mono<Void> deleteById(int productId);
}
