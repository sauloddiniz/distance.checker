package com.distancechecker.service;

import com.distancechecker.client.ConnectGeolocationApi;
import com.distancechecker.dto.*;
import com.distancechecker.exceptions.AddressBlankException;
import com.distancechecker.exceptions.GenericAddressException;
import com.distancechecker.exceptions.InsufficientAddressException;
import com.distancechecker.exceptions.ZeroResultsAddressException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;

import static com.distancechecker.utils.MessageUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    ConnectGeolocationApi geolocationApi;
    @Spy
    CalculationDistance calculationDistance;
    @InjectMocks
    AddressService service;

    ResponseGeolocationApiDto responseGeolocationApiDto;

    @BeforeEach
    public void init() {

        Random random = new Random();
        double lat = random.nextDouble() * -90.0;
        double lng = random.nextDouble() * -180.0;

        responseGeolocationApiDto =
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of(ResultDto
                                .builder()
                                .formattedAddress("R. inventada: lat" + lat + " lng: " + lng)
                                .geometry(GeometryDto
                                        .builder()
                                        .location(LocationDto
                                                .builder()
                                                .lat(lat)
                                                .lng(lng)
                                                .build())
                                        .build())
                                .build()))
                        .status("OK")
                        .build();
    }

    @Test
    void whenMountListAddressReceiveValuesBlank() {

        String messageReturn = ADDRESS_BLANK_EXCEPTION;
        String inputAddress = "";

        Exception exception = assertThrows(AddressBlankException.class, () -> service.mountListAddress(inputAddress));

        assertEquals(messageReturn, exception.getMessage());
    }

    @Test
    void whenMountListAddressReceiveValuesButNotContainsSemicolon() {

        String messageReturn = GENERIC_ADDRESS_EXCEPTION;
        String inputAddress = "Av Rio Branco, 1, Centro Rio de Janeiro RJ" +
                "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ" +
                "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ";

        Exception exception = assertThrows(GenericAddressException.class,
                () -> service.mountListAddress(inputAddress));

        assertEquals(messageReturn, exception.getMessage());
    }

    @Test
    void whenMountListAddressReceiveValuesButNotContainsOneSemicolon() {

        String messageReturn = INSUFFICIENT_ADDRESS_EXCEPTION;
        String inputAddress = "Av Rio Branco, 1, Centro Rio de Janeiro RJ;" +
                "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ" +
                "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ";


        Exception exception = assertThrows(InsufficientAddressException.class,
                () -> service.mountListAddress(inputAddress));

        assertEquals(messageReturn, exception.getMessage());
    }

    @Test
    void whenMountListAddressReceiveAddressNotExist() {

        ResponseGeolocationApiDto firstReturn =
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of())
                        .status("ZERO_RESULTS")
                        .build();

        String messageReturn = ZERO_RESULTS_MESSAGE;
        String inputAddress = "AAAAAAA;" +
                "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ;" +
                "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ;";

        when(geolocationApi.getGeolocationByAddress(anyString()))
                .thenReturn(firstReturn);

        Exception exception = assertThrows(ZeroResultsAddressException.class,
                () -> service.mountListAddress(inputAddress));

        assertEquals(messageReturn, exception.getMessage());
    }
    @Test
    void whenMountListReceivedThirdCorrectAddress() {

        int sizeList = 3;

        Random random = new Random();

        ResponseGeolocationApiDto firstReturn =
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
                        .build();

        ResponseGeolocationApiDto secondReturn =
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
                        .build();

        ResponseGeolocationApiDto thirdReturn =
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
                        .build();

        String inputAddress = "Rua das Goiabas;" +
                "Rua das Aboboras;" +
                "Rua das Mandiocas;";

        when(geolocationApi.getGeolocationByAddress(anyString()))
                .thenReturn(firstReturn)
                .thenReturn(secondReturn)
                .thenReturn(thirdReturn);

        ResponseDto responseDto = service.mountListAddress(inputAddress);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(sizeList,responseDto.getDistanceAddressList().size());
    }
}