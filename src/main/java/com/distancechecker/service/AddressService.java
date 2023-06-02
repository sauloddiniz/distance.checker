package com.distancechecker.service;

import com.distancechecker.client.ConnectGeolocationApi;
import com.distancechecker.dto.AddressComparableDto;
import com.distancechecker.dto.AddressFullDto;
import com.distancechecker.dto.ResponseDto;
import com.distancechecker.dto.ResponseGeolocationApiDto;
import com.distancechecker.exceptions.AddressBlankException;
import com.distancechecker.exceptions.GenericAddressException;
import com.distancechecker.exceptions.InsufficientAddressException;
import com.distancechecker.exceptions.ZeroResultsAddressException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.distancechecker.utils.ValuesUtils.ZERO_RESULTS;

@Service
@Slf4j
public class AddressService {

    private final ConnectGeolocationApi geolocationApi;
    private final CalculationDistance calculationDistance;

    @Autowired
    public AddressService(ConnectGeolocationApi geolocationApi, CalculationDistance calculationDistance) {
        this.geolocationApi = geolocationApi;
        this.calculationDistance = calculationDistance;
    }
    public ResponseDto mountListAddress(String addresses) {

        List<String> listAddress = verifyAsListValueAddress(addresses);

        List<ResponseGeolocationApiDto> responseApi =
                listAddress.stream().map(this::getGeolocation).toList();

        verifyStatusListResponseApi(responseApi);

        List<AddressComparableDto> listAddressInOrder = calculationDistance.calcDistance(responseApi);

        AddressFullDto nearestAddress = calculationDistance.getFirstElement(listAddressInOrder);

        AddressFullDto farthestAddress = calculationDistance.getLastElement(listAddressInOrder);

        return new ResponseDto(nearestAddress, farthestAddress,listAddressInOrder);
    }

    private void verifyStatusListResponseApi(List<ResponseGeolocationApiDto> responseApi) {
        responseApi.forEach(e -> {
            if (e.getStatus().equals(ZERO_RESULTS)) {
                throw new ZeroResultsAddressException();
            }
        });
    }

    private List<String> verifyAsListValueAddress(String addresses) {
        if (addresses.contains(";")) {
            List<String> listAddress = Arrays.asList(addresses.split(";"));
            if (listAddress.size() < 3) {
                throw new InsufficientAddressException();
            }
            return listAddress;
         } else {
            if (addresses.isEmpty()) {
                throw new AddressBlankException();
            }
        }
        throw new GenericAddressException();
    }

    private ResponseGeolocationApiDto getGeolocation(String address) {
        return geolocationApi.getGeolocationByAddress(address);
    }
}
