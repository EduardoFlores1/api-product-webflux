package com.api.product_webflux.service;

import com.api.product_webflux.dto.ProductRequestDTO;
import com.api.product_webflux.dto.ProductResponseDTO;
import com.api.product_webflux.repository.IProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    private final static String NF_MESSAGE = "Product not found";
    private final static String NAME_MESSAGE = "Product name already in use";

    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<ProductResponseDTO> getAll() {
        return null;
    }

    @Override
    public Mono<ProductResponseDTO> getById(int productId) {
        return null;
    }

    @Override
    public Mono<ProductResponseDTO> save(ProductRequestDTO createProduct) {
        return null;
    }

    @Override
    public Mono<ProductResponseDTO> update(int productId, ProductRequestDTO updateProduct) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(int productId) {
        return null;
    }
}
