package com.distancechecker.client.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class ConnectClientConfig {

    @Bean
    ErrorDecoder clientErrorDecoder() {
        return new ConnectErrorDecoder();
    }

    @Bean
    public CustomInterceptor interceptor() {
        return new CustomInterceptor();
    }
    @Bean
    public RetryTemplate retryTemplate() {
        return new RetryTemplate();
    }

    @Bean
    public Logger.Level feignLogger(){
        return Logger.Level.FULL;
    }
}
