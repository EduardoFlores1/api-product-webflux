package com.api.product_webflux.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProductDTO(
        @NotBlank(message = "Name is mandatory")
        @Size(min = 3, max = 30, message = "Name must be min 3 and max 30 characters")
        String Name,

        @Min(value = 1, message = "Price must be greater then zero")
        @Max(value = 100, message = "Price must not be greater then 100")
        double Price
) {};
