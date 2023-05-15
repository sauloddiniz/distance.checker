package com.distancechecker.config;

import com.distancechecker.client.ConnectGeolocationApi;
import com.distancechecker.dto.ResponseGeolocationApiDto;
import com.distancechecker.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CacheTest {

    @Autowired
    AddressService addressService;

    @Autowired
    CacheManager cacheManager;

    @Test
    public void testCache() {

        ResponseGeolocationApiDto address = addressService.getAddress("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG");
        ResponseGeolocationApiDto address2 = addressService.getAddress("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG");
        ResponseGeolocationApiDto address3 = addressService.getAddress("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG");

        Assertions.assertEquals(address, address2);
        Assertions.assertEquals(address2, address3);

        Cache cache = cacheManager.getCache("address-cached");
        assertNotNull(cache.get("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG"));
    }
}