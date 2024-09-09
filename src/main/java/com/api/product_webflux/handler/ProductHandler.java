package com.api.product_webflux.handler;

import com.api.product_webflux.dto.CreateProductDTO;
import com.api.product_webflux.dto.ProductResponseDTO;
import com.api.product_webflux.dto.UpdateProductDTO;
import com.api.product_webflux.service.IProductService;
import com.api.product_webflux.validator.ObjectValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {
    private final IProductService productService;
    private final ObjectValidator objectValidator;

    public ProductHandler(IProductService productService, ObjectValidator objectValidator) {
        this.productService = productService;
        this.objectValidator = objectValidator;
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<ProductResponseDTO> products = productService.getAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(products, ProductResponseDTO.class);
    }

    public Mono<ServerResponse> getOne(ServerRequest request) {
        int productId = Integer.parseInt(request.pathVariable("productId"));
        Mono<ProductResponseDTO> product = productService.getById(productId);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(product, ProductResponseDTO.class);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<CreateProductDTO> dtoMono = request
                .bodyToMono(CreateProductDTO.class)
                .doOnNext(objectValidator::validate);

        return dtoMono.flatMap(dto ->
                ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productService.save(dto), ProductResponseDTO.class)
                );
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        int productId = Integer.parseInt(request.pathVariable("productId"));
        Mono<UpdateProductDTO> dtoMono = request
                .bodyToMono(UpdateProductDTO.class)
                .doOnNext(objectValidator::validate);

        return dtoMono.flatMap(dto ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productService.update(productId, dto), ProductResponseDTO.class)
        );
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        int productId = Integer.parseInt(request.pathVariable("productId"));
        return ServerResponse
                .status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.deleteById(productId), Void.class);
    }
}
