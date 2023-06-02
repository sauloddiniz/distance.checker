package com.distancechecker.config;

import com.distancechecker.client.ConnectGeolocationApi;
import com.distancechecker.dto.ResponseGeolocationApiDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Disabled
class CacheTest {

    @Autowired
    ConnectGeolocationApi geolocationApi;

    @Autowired
    CacheManager cacheManager;

    @Test
    void testCache() {

        Cache cache = cacheManager.getCache("address-cached");
        cache.clear();

        ResponseGeolocationApiDto address = geolocationApi.getGeolocationByAddress("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG");
        ResponseGeolocationApiDto address2 = geolocationApi.getGeolocationByAddress("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG");
        ResponseGeolocationApiDto address3 = geolocationApi.getGeolocationByAddress("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG");

        Assertions.assertEquals(address, address2);
        Assertions.assertEquals(address2, address3);

        assertNotNull(cache.get("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG"));
    }
}