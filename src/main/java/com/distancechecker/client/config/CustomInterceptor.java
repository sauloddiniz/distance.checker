package com.distancechecker.client.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class CustomInterceptor implements RequestInterceptor {

    @Value("${api.key}")
    String paramValue;
    String param = "key";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query(param,paramValue);
    }
}
