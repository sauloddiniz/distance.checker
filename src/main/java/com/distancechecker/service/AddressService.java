package com.distancechecker.service;

import com.distancechecker.client.ConnectGeolocationApi;
import com.distancechecker.dto.AddressComparableDto;
import com.distancechecker.dto.GeometryDto;
import com.distancechecker.dto.ResponseGeolocationApiDto;
import dev.loqo71la.haversine.Coordinate;
import dev.loqo71la.haversine.Haversine;
import dev.loqo71la.haversine.Unit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.distancechecker.utils.UtilValues.FIRST_POSITION;

@Service
@Slf4j
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
        log.info("getListAddress -> AddressService");
        List<String> listAddress = verifyAsListValueAddress(addresses);

        List<ResponseGeolocationApiDto> responseApi =
                listAddress.stream().map(e -> getAddress(e)).collect(Collectors.toList());

        List<AddressComparableDto>  addressComparables = calculateDistance(responseApi);

        return responseApi;
    }

    private List<AddressComparableDto> calculateDistance(List<ResponseGeolocationApiDto> responseApi) {

        List<AddressComparableDto> list = new ArrayList<>();

        for (int i =1; i < responseApi.size(); i++) {
            for (int y = 0; y < i; y++) {
                if (i!=y) {
                    GeometryDto geometryA = responseApi.get(i).results.get(FIRST_POSITION).getGeometry();
                    GeometryDto geometryB = responseApi.get(y).results.get(FIRST_POSITION).getGeometry();

                    Coordinate coordinateA = new Coordinate(geometryA.getLocation().lat,
                            geometryA.getLocation().lng);
                    Coordinate coordinateB = new Coordinate(geometryB.getLocation().lat,
                            geometryB.getLocation().lng);

                    double distance = Haversine.calculateDistance(coordinateA, coordinateB, Unit.Kilometers);
                    String addressA = responseApi.get(i).results.get(FIRST_POSITION).formattedAddress;
                    String addressB = responseApi.get(y).results.get(FIRST_POSITION).formattedAddress;

                    list.add(new AddressComparableDto(distance, addressA, addressB));

                    log.info("distance");
                }
            }
        }

        return null;
    }

    private List<String> verifyAsListValueAddress(String addresses) {
        if (!addresses.isEmpty() || addresses.contains(";")) {
            return Arrays.asList(addresses.split(";"));
        } else return Arrays.asList(addresses);
    }
}
