package com.api.product_webflux.router;

import com.api.product_webflux.handler.ProductHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProductRouter {

    private static final String PATH = "api/v1/products";

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

    @Bean
    RouterFunction<ServerResponse> router(ProductHandler productHandler) {
        return RouterFunctions.route()
                .GET(PATH, productHandler::getAll)
                .GET(PATH + "/{productId}", productHandler::getOne)
                .POST(PATH, productHandler::save)
                .PUT(PATH + "/{productId}", productHandler::update)
                .DELETE(PATH + "/{productId}", productHandler::deleteById)
                .build();
    }
}
