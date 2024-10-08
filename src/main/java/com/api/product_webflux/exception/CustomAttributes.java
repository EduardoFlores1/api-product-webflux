package com.api.product_webflux.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {

        Map<String, Object> errorAttributes = new HashMap<>();
        Throwable throwable = super.getError(webRequest);

        if(throwable instanceof CustomException customException) {
            errorAttributes.put("status", customException.getStatus());
            errorAttributes.put("message", customException.getMessage());
        }

        return errorAttributes;
    }
}
