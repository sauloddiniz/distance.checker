package com.distancechecker.service;

import com.distancechecker.dto.AddressComparableDto;
import com.distancechecker.dto.AddressFullDto;
import com.distancechecker.dto.GeometryDto;
import com.distancechecker.dto.ResponseGeolocationApiDto;
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

    public List<AddressComparableDto> calcDistance(List<ResponseGeolocationApiDto> responseApi) {

        List<AddressComparableDto> list = new ArrayList<>();

        for (int i = 0; i < responseApi.size(); i++) {
            for (int y = 0; y < i; y++) {
                if (isNotEqual(i,y)) {
                    GeometryDto addressAGeometry = getGeometry(responseApi, y);
                    GeometryDto addressBGeometry = getGeometry(responseApi, i);

                    Coordinate coordinateA = createCoordinate(addressAGeometry);
                    Coordinate coordinateB = createCoordinate(addressBGeometry);

                    double distance = Haversine.calculateDistance(coordinateA, coordinateB, Unit.Kilometers);
                    String addressA = getFormattedAddress(responseApi, y);
                    String addressB = getFormattedAddress(responseApi, i);

                    list.add(new AddressComparableDto(distance, addressA, addressB));
                }
            }
        }

        list.sort(Comparator.naturalOrder());

        return list;
    }

    private boolean isNotEqual(int i, int y) {
        return i != y;
    }

    private static String getFormattedAddress(List<ResponseGeolocationApiDto> responseApi, int position) {
        Optional<String> optionalCompleteAddress = Optional
                .ofNullable(responseApi.get(position).getResults().get(FIRST_POSITION).getFormattedAddress());
        return optionalCompleteAddress.orElseThrow(FormattedAddressNullException::new);
    }

    private static GeometryDto getGeometry(List<ResponseGeolocationApiDto> responseApi, int position) {
        Optional<GeometryDto> optionalGeometry = Optional
                .ofNullable(responseApi.get(position).getResults().get(FIRST_POSITION).getGeometry());
        return optionalGeometry.orElseThrow(AddressGeometryNullException::new);
    }

    private static Coordinate createCoordinate(GeometryDto geometry) {
        double lat = geometry.getLocation().getLat() != 0.0 ?
                geometry.getLocation().getLat() : throwException("Latitude");
        double lng = geometry.getLocation().getLng() != 0.0 ?
                geometry.getLocation().getLng() : throwException("Longitude");
        return new Coordinate(lat, lng);
    }

    private static Double throwException(String latOrLng) {
        throw new AddressGeolocationNullException(latOrLng);
    }

    public AddressFullDto getFirstElement(List<AddressComparableDto> listAddressInOrder) {
        return new AddressFullDto(listAddressInOrder.get(FIRST_POSITION));
    }

    public AddressFullDto getLastElement(List<AddressComparableDto> listAddressInOrder) {
        int lastPosition = listAddressInOrder.size() - 1;
        return new AddressFullDto(listAddressInOrder.get(lastPosition));
    }
}
