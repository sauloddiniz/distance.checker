package com.distancechecker.client;

import com.distancechecker.dto.ResponseGeolocationApiDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "geolocation-google-api",
        url = "${api.url}${api.type}?key=${api.key}",
        configuration = ConnectClientConfig.class)
public interface ConnectGeolocationApi {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseGeolocationApiDto getGeolocationByAddress(@RequestParam("address") final String address);
}
