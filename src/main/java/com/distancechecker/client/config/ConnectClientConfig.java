package com.distancechecker.client.config;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class ConnectClientConfig {

    @Bean
    ErrorDecoder clientErrorDecoder() {
        return new ConnectErrorDecoder();
    }

    @Bean
    public Logger.Level feignLogger(){
        return Logger.Level.FULL;
    }
}
