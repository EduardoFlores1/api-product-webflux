package com.api.product_webflux.validator;

import com.api.product_webflux.exception.CustomException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator {
    private final Validator validator;

    public ObjectValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> T validate(T object) {
        try {
            Set<ConstraintViolation<T>> errors = validator.validate(object);
            if (errors.isEmpty()) {
                return object;
            } else {
                String message = errors.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(", "));
                throw new CustomException(HttpStatus.BAD_REQUEST, message);
            }
        } catch (Exception e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
