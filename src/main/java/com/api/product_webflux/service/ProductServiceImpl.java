package com.api.product_webflux.service;

import com.api.product_webflux.dto.CreateProductDTO;
import com.api.product_webflux.dto.ProductResponseDTO;
import com.api.product_webflux.dto.UpdateProductDTO;
import com.api.product_webflux.exception.CustomException;
import com.api.product_webflux.mapper.ProductMapper;
import com.api.product_webflux.repository.IProductRepository;
import org.springframework.http.HttpStatus;
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
        return productRepository.findAll()
                .doOnNext(entity -> System.out.println(entity))
                .map(ProductMapper::fromEntityToResponse);
    }

    @Override
    public Mono<ProductResponseDTO> getById(int productId) {
        return productRepository.findById(productId)
                .map(ProductMapper::fromEntityToResponse)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE)));
    }

    @Override
    public Mono<ProductResponseDTO> save(CreateProductDTO createProduct) {
        Mono<Boolean> existName = productRepository.findByName(createProduct.Name()).hasElement();
        return existName.flatMap(exists -> exists ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, NAME_MESSAGE))
                : productRepository.save(ProductMapper.fromCreateToEntity(createProduct))
                .map(ProductMapper::fromEntityToResponse));
    }

    @Override
    public Mono<ProductResponseDTO> update(int productId, UpdateProductDTO updateProduct) {
        Mono<Boolean> productById = productRepository.findById(productId).hasElement();
        Mono<Boolean> productRepeatedName = productRepository.repeatedName(productId, updateProduct.Name()).hasElement();

        return productById.flatMap(existsId -> existsId
                ? productRepeatedName.flatMap(existsName -> existsName
                                            ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, NF_MESSAGE))
                                            : productRepository.save(ProductMapper.fromUpdateToEntity(productId, updateProduct))
                                                .map(ProductMapper::fromEntityToResponse))
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE)));
    }

    @Override
    public Mono<Void> deleteById(int productId) {
        Mono<Boolean> productById = productRepository.findById(productId).hasElement();
        return productById.flatMap(exists -> exists ? productRepository.deleteById(productId)
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, NF_MESSAGE)));
    }
}
