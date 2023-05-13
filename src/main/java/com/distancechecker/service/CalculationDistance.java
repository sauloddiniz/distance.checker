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

        for (int i =0; i < responseApi.size(); i++) {
            for (int y = 0; y < i; y++) {
                if (i!=y) {
                    GeometryDto geometryA = responseApi.get(y).results.get(FIRST_POSITION).getGeometry();
                    GeometryDto geometryB = responseApi.get(i).results.get(FIRST_POSITION).getGeometry();

                    Coordinate coordinateA = new Coordinate(geometryA.getLocation().lat,
                            geometryA.getLocation().lng);
                    Coordinate coordinateB = new Coordinate(geometryB.getLocation().lat,
                            geometryB.getLocation().lng);


                    double distance = Haversine.calculateDistance(coordinateA, coordinateB, Unit.Kilometers);
                    String addressA = responseApi.get(y).results.get(FIRST_POSITION).formattedAddress;
                    String addressB = responseApi.get(i).results.get(FIRST_POSITION).formattedAddress;

                    list.add(new AddressComparableDto(distance, addressA, addressB));
                }
            }
        }

        Collections.sort(list, Comparator.naturalOrder());

        return list;
    }

    public AddressFullDto getFirstElement(List<AddressComparableDto> listAddressInOrder) {
        return new AddressFullDto(listAddressInOrder.get(FIRST_POSITION));
    }

    public AddressFullDto getLastElement(List<AddressComparableDto> listAddressInOrder) {
        int LAST_POSITION = listAddressInOrder.size() - 1;
        return new AddressFullDto(listAddressInOrder.get(LAST_POSITION));
    }
}
