package com.distancechecker.client;

import com.distancechecker.client.config.ConnectClientConfig;
import com.distancechecker.dto.ResponseGeolocationApiDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geolocation-google-api",
        url = "${api.url}${api.type.response}?",
        configuration = ConnectClientConfig.class)
public interface ConnectGeolocationApi {

    @Cacheable("address-cached")
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseGeolocationApiDto getGeolocationByAddress(@RequestParam("address") final String address);
}