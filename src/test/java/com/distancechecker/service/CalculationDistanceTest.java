package com.distancechecker.service;

import com.distancechecker.dto.*;
import com.distancechecker.exceptions.AddressGeolocationNullException;
import com.distancechecker.exceptions.AddressGeometryNullException;
import com.distancechecker.exceptions.FormattedAddressNullException;
import org.junit.jupiter.api.Assertions;
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
        String expectedMessageReturn = ADDRESS_GEOMETRY_IS_NULL;

        List<ResponseGeolocationApiDto> responseApi =
                List.of(
                        createResponseGeolocation("Rua das Goiabas", random),
                        createResponseGeolocation("Rua das Aboboras", random),
                        createResponseGeolocation("Rua das Mandiocas", (GeometryDto) null)
                );

        Exception exception = assertThrows(AddressGeometryNullException.class,
                () -> calculationDistance.calc(responseApi));

        assertEquals(expectedMessageReturn, exception.getMessage());
    }

    @Test
    void whenGeometryNotProviderLatitudeValue() {

        Random random = new Random();
        String expectedMessageReturn = ADDRESS_GEOLOCATION_NUMBER_IS_NULL + " Latitude";
        LocationDto location = new LocationDto(0.0,random.nextDouble() * -180.0);

        List<ResponseGeolocationApiDto> responseApi =
                List.of(
                        createResponseGeolocation("Rua das Goiabas", random),
                        createResponseGeolocation("Rua das Aboboras", random),
                        createResponseGeolocation("Rua das Mandiocas", location)
                );

        Exception exception = assertThrows(AddressGeolocationNullException.class,
                () -> calculationDistance.calc(responseApi));

        assertEquals(expectedMessageReturn, exception.getMessage());
    }

    @Test
    void whenGeometryNotProviderLongitudeValue() {

        Random random = new Random();
        String expectedMessageReturn = ADDRESS_GEOLOCATION_NUMBER_IS_NULL + " Longitude";
        LocationDto location = new LocationDto(random.nextDouble() * -90.0,0.0);

        List<ResponseGeolocationApiDto> responseApi =
                List.of(
                        createResponseGeolocation("Rua das Goiabas", random),
                        createResponseGeolocation("Rua das Aboboras", random),
                        createResponseGeolocation("Rua das Mandiocas", location)
                );

        Exception exception = assertThrows(AddressGeolocationNullException.class,
                () -> calculationDistance.calc(responseApi));

        assertEquals(expectedMessageReturn, exception.getMessage());
    }

    @Test
    void whenAddressNotProviderFormattedAddressValue() {

        Random random = new Random();
        String expectedMessageReturn = ADDRESS_FORMATTED_ADDRESS_IS_NULL;

        List<ResponseGeolocationApiDto> responseApi =
                List.of(
                        createResponseGeolocation(null, random),
                        createResponseGeolocation("Rua das Aboboras", random),
                        createResponseGeolocation("Rua das Mandiocas", random)
                );

        Exception exception = assertThrows(FormattedAddressNullException.class,
                () -> calculationDistance.calc(responseApi));

        assertEquals(expectedMessageReturn, exception.getMessage());
    }

    @Test
    void whenComparableNearestAddress() {

        AddressFullDto expectedNearestAddress =
                new AddressFullDto(7.430046995476972,
                        "Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG",
                        "Rua das Guianas ,31, Santa Cecilia, Timoteo - MG");
        AddressFullDto farthestAddress =
                new AddressFullDto(16.924477011219285,
                        "Rua das Guianas ,31, Santa Cecilia, Timoteo - MG",
                        "Avenida Carlos Chagas, 789 - Cidade Nobre, Ipatinga - MG");

        List<ResponseGeolocationApiDto> responseApi = List.of(
                createResponseGeolocation("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG",
                        new LocationDto(-19.5248364,-42.6261742)),
                createResponseGeolocation("Rua das Guianas ,31, Santa Cecilia, Timoteo - MG",
                        new LocationDto(-19.5865592, -42.6533382)),
                createResponseGeolocation("Avenida Carlos Chagas, 789 - Cidade Nobre, Ipatinga - MG",
                        new LocationDto(-19.4626085,-42.5596159))
        );

        List<AddressComparableDto> listAddressInOrder = calculationDistance.calc(responseApi);

        AddressFullDto firstElement = calculationDistance.getFirstElement(listAddressInOrder);

        Assertions.assertNotNull(firstElement);
        Assertions.assertEquals(firstElement,expectedNearestAddress);
    }
    @Test
    void whenComparableFarthestAddress() {

        AddressFullDto expectedFarthestAddress =
                new AddressFullDto(16.924477011219285,
                        "Rua das Guianas ,31, Santa Cecilia, Timoteo - MG",
                        "Avenida Carlos Chagas, 789 - Cidade Nobre, Ipatinga - MG");

        List<ResponseGeolocationApiDto> responseApi = List.of(
                createResponseGeolocation("Rua Duque de Caxias, 264, Centro, Coronel Fabriciano - MG",
                        new LocationDto(-19.5248364,-42.6261742)),
                createResponseGeolocation("Rua das Guianas ,31, Santa Cecilia, Timoteo - MG",
                        new LocationDto(-19.5865592, -42.6533382)),
                createResponseGeolocation("Avenida Carlos Chagas, 789 - Cidade Nobre, Ipatinga - MG",
                        new LocationDto(-19.4626085,-42.5596159))
        );

        List<AddressComparableDto> listAddressInOrder = calculationDistance.calc(responseApi);

        AddressFullDto lastElement = calculationDistance.getLastElement(listAddressInOrder);

        Assertions.assertNotNull(lastElement);
        Assertions.assertEquals(lastElement,expectedFarthestAddress);
    }

    private static ResponseGeolocationApiDto createResponseGeolocation(String formattedAddress, Random random) {
        return ResponseGeolocationApiDto
                .builder()
                .results(List.of(ResultDto
                        .builder()
                        .formattedAddress(formattedAddress)
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
                .build();
    }

    private static ResponseGeolocationApiDto createResponseGeolocation(String formattedAddress, GeometryDto geometry) {
        return ResponseGeolocationApiDto
                .builder()
                .results(List.of(ResultDto
                        .builder()
                        .formattedAddress(formattedAddress)
                        .geometry(geometry)
                        .build()))
                .status("OK")
                .build();
    }
    private static ResponseGeolocationApiDto createResponseGeolocation(String formattedAddress, LocationDto location) {
        return ResponseGeolocationApiDto
                .builder()
                .results(List.of(ResultDto
                        .builder()
                        .formattedAddress(formattedAddress)
                        .geometry(GeometryDto
                                .builder()
                                .location(location)
                                .build())
                        .build()))
                .status("OK")
                .build();
    }

}