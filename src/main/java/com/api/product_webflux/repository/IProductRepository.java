package com.api.product_webflux.repository;

import com.api.product_webflux.entity.ProductEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IProductRepository extends ReactiveCrudRepository<ProductEntity, Integer> {
    Mono<ProductEntity> findByName(String name);

    @Query("SELECT * FROM Products WHERE ProductId <> :id AND Name = :name")
    Mono<ProductEntity> repeatedName(int productId, String name);
}
