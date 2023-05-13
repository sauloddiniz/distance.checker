package com.distancechecker.service;

import com.distancechecker.dto.AddressComparableDto;
import com.distancechecker.dto.AddressFullDto;
import com.distancechecker.dto.GeometryDto;
import com.distancechecker.dto.ResponseGeolocationApiDto;
import dev.loqo71la.haversine.Coordinate;
import dev.loqo71la.haversine.Haversine;
import dev.loqo71la.haversine.Unit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
                GeometryDto geometryA = getGeometry(responseApi, y);
                GeometryDto geometryB = getGeometry(responseApi, i);

                Coordinate coordinateA = createCoordinate(geometryA);
                Coordinate coordinateB = createCoordinate(geometryB);

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
        return responseApi.get(position).results.get(FIRST_POSITION).formattedAddress;
    }

    private static GeometryDto getGeometry(List<ResponseGeolocationApiDto> responseApi, int position) {
        return responseApi.get(position).results.get(FIRST_POSITION).getGeometry();
    }

    private static Coordinate createCoordinate(GeometryDto geometry) {
        return new Coordinate(geometry.getLocation().lat,
                geometry.getLocation().lng);
    }

    public AddressFullDto getFirstElement(List<AddressComparableDto> listAddressInOrder) {
        return new AddressFullDto(listAddressInOrder.get(FIRST_POSITION));
    }

    public AddressFullDto getLastElement(List<AddressComparableDto> listAddressInOrder) {
        int LAST_POSITION = listAddressInOrder.size() - 1;
        return new AddressFullDto(listAddressInOrder.get(LAST_POSITION));
    }
}
