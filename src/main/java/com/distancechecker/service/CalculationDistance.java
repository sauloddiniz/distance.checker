package com.distancechecker.service;

import com.distancechecker.dto.AddressComparableDto;
import com.distancechecker.dto.AddressFullDto;
import com.distancechecker.dto.GeometryDto;
import com.distancechecker.dto.ResponseGeolocationApiDto;
import com.distancechecker.exceptions.AddressBlankException;
import com.distancechecker.exceptions.AddressGeolocationNullException;
import com.distancechecker.exceptions.AddressGeometryNullException;
import com.distancechecker.exceptions.FormattedAddressNullException;
import dev.loqo71la.haversine.Coordinate;
import dev.loqo71la.haversine.Haversine;
import dev.loqo71la.haversine.Unit;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.distancechecker.utils.ValuesUtils.FIRST_POSITION;

@Service
public class CalculationDistance {

    public List<AddressComparableDto> calc(List<ResponseGeolocationApiDto> responseApi) {

        List<AddressComparableDto> list = new ArrayList<>();

        for (int i = 0; i < responseApi.size(); i++) {
            for (int y = 0; y < i; y++) {
                if (i == y) {
                    continue;
                }
                GeometryDto addressGeometryA = getGeometry(responseApi, y);
                GeometryDto addressGeometryB = getGeometry(responseApi, i);

                Coordinate coordinateA = createCoordinate(addressGeometryA);
                Coordinate coordinateB = createCoordinate(addressGeometryB);

                double distance = Haversine.calculateDistance(coordinateA, coordinateB, Unit.Kilometers);
                String addressA = getFormattedAddress(responseApi, y);
                String addressB = getFormattedAddress(responseApi, i);

                list.add(new AddressComparableDto(distance, addressA, addressB));
            }
        }

        Collections.sort(list, Comparator.naturalOrder());

        return list;
    }

    private static String getFormattedAddress(List<ResponseGeolocationApiDto> responseApi, int position) {
        Optional<String> optionalCompleteAddress = Optional
                .ofNullable(responseApi.get(position).results.get(FIRST_POSITION).formattedAddress);
        return optionalCompleteAddress.orElseThrow(() -> new FormattedAddressNullException());
    }

    private static GeometryDto getGeometry(List<ResponseGeolocationApiDto> responseApi, int position) {
        Optional<GeometryDto> optionalGeometry = Optional
                .ofNullable(responseApi.get(position).results.get(FIRST_POSITION).getGeometry());
        return optionalGeometry.orElseThrow(() -> new AddressGeometryNullException());
    }

    private static Coordinate createCoordinate(GeometryDto geometry) {
        Optional<Double> optionalLat = Optional.ofNullable(geometry.getLocation().lat);
        Optional<Double> optionalLng = Optional.ofNullable(geometry.getLocation().lng);
        return new Coordinate(optionalLat.orElseThrow(() -> new AddressGeolocationNullException("Latitude")),
                optionalLng.orElseThrow(() -> new AddressGeolocationNullException("Longitude")));
    }

    public AddressFullDto getFirstElement(List<AddressComparableDto> listAddressInOrder) {
        return new AddressFullDto(listAddressInOrder.get(FIRST_POSITION));
    }

    public AddressFullDto getLastElement(List<AddressComparableDto> listAddressInOrder) {
        int LAST_POSITION = listAddressInOrder.size() - 1;
        return new AddressFullDto(listAddressInOrder.get(LAST_POSITION));
    }
}
