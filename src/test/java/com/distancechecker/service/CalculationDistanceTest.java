package com.distancechecker.service;

import com.distancechecker.dto.*;
import com.distancechecker.exceptions.AddressGeolocationNullException;
import com.distancechecker.exceptions.AddressGeometryNullException;
import com.distancechecker.exceptions.FormattedAddressNullException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;

import static com.distancechecker.utils.MessageUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CalculationDistanceTest {

    CalculationDistance calculationDistance = new CalculationDistance();

    @Test
    void whenAddressNotProviderGeometryValue() {

        Random random = new Random();
        String messageReturn = ADDRESS_GEOMETRY_IS_NULL;

        List<ResponseGeolocationApiDto> responseApi = List.of(
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Goiabas")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .lng(random.nextDouble() * -180.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build(),
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Aboboras")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .lng(random.nextDouble() * -180.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build(),
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Mandiocas")
                                .geometry(null)
                                .build()))
                        .status("OK")
                        .build()
        );

        Exception exception = assertThrows(AddressGeometryNullException.class,
                () -> calculationDistance.calc(responseApi));

        assertEquals(messageReturn, exception.getMessage());
    }

    @Test
    void whenGeometryNotProviderLatitudeValue() {

        Random random = new Random();
        String messageReturn = ADDRESS_GEOLOCATION_NUMBER_IS_NULL + " Latitude";

        List<ResponseGeolocationApiDto> responseApi = List.of(
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Goiabas")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .lng(random.nextDouble() * -180.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build(),
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Aboboras")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build(),
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Mandiocas")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .lng(random.nextDouble() * -180.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build()
        );

        Exception exception = assertThrows(AddressGeolocationNullException.class,
                () -> calculationDistance.calc(responseApi));

        assertEquals(messageReturn, exception.getMessage());
    }
    @Test
    void whenGeometryNotProviderLongitudeValue() {

        Random random = new Random();
        String messageReturn = ADDRESS_GEOLOCATION_NUMBER_IS_NULL + " Longitude";

        List<ResponseGeolocationApiDto> responseApi = List.of(
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Goiabas")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .lng(random.nextDouble() * -180.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build(),
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Aboboras")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build(),
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Mandiocas")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .lng(random.nextDouble() * -180.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build()
        );

        Exception exception = assertThrows(AddressGeolocationNullException.class,
                () -> calculationDistance.calc(responseApi));

        assertEquals(messageReturn, exception.getMessage());
    }

    @Test
    void whenAddressNotProviderFormattedAddressValue() {

        Random random = new Random();
        String messageReturn = ADDRESS_FORMATTED_ADDRESS_IS_NULL;

        List<ResponseGeolocationApiDto> responseApi = List.of(
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress(null)
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .lng(random.nextDouble() * -180.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build(),
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Aboboras")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .lng(random.nextDouble() * -180.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build(),
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("Rua das Mandiocas")
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(random.nextDouble() * -90.0)
                                                .lng(random.nextDouble() * -180.0)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build()
        );

        Exception exception = assertThrows(FormattedAddressNullException.class,
                () -> calculationDistance.calc(responseApi));

        assertEquals(messageReturn, exception.getMessage());
    }

}