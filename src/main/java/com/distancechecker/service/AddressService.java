package com.distancechecker.service;

import com.distancechecker.client.ConnectGeolocationApi;
import com.distancechecker.dto.ResponseGeolocationApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final ConnectGeolocationApi geolocationApi;

    @Autowired
    public AddressService(ConnectGeolocationApi geolocationApi) {
        this.geolocationApi = geolocationApi;
    }



    private ResponseGeolocationApiDto getAddress(String address) {
        return geolocationApi.getGeolocationByAddress(address).getBody();
    }

    public Object getListAddress(String addresses) {
        List<String> listAddress = verifyAsListValueAddress(addresses);

        List<ResponseGeolocationApiDto> responseApi =
                listAddress.stream().map(e -> getAddress(e)).collect(Collectors.toList());
        return responseApi;
    }

    private List<String> verifyAsListValueAddress(String addresses) {
        if (!addresses.isEmpty() || addresses.contains(";")) {
            return Arrays.asList(addresses.split(";"));
        } else return Arrays.asList(addresses);
    }
}
