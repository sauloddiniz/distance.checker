package com.distancechecker.service;

import com.distancechecker.client.ConnectGeolocationApi;
import com.distancechecker.dto.*;
import com.distancechecker.exceptions.AddressBlankException;
import com.distancechecker.exceptions.GenericAddressException;
import com.distancechecker.exceptions.InsufficientAddressException;
import com.distancechecker.exceptions.ZeroResultsAddressException;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void whenMountListAddressReceiveValuesBlank() {

        String expectedMessageReturn = ADDRESS_BLANK_EXCEPTION;
        String inputAddress = "";

        Exception exception = assertThrows(AddressBlankException.class,
                () -> service.mountListAddress(inputAddress));

        assertEquals(expectedMessageReturn, exception.getMessage());
    }

    @Test
    void whenMountListAddressReceiveValuesButNotContainsSemicolon() {

        String expectedMessageReturn = GENERIC_ADDRESS_EXCEPTION;
        String inputAddress = "Av Rio Branco, 1, Centro Rio de Janeiro RJ" +
                "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ" +
                "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ";

        Exception exception = assertThrows(GenericAddressException.class,
                () -> service.mountListAddress(inputAddress));

        assertEquals(expectedMessageReturn, exception.getMessage());
    }

    @Test
    void whenMountListAddressReceiveValuesButNotContainsOneSemicolon() {

        String expectedMessageReturn = INSUFFICIENT_ADDRESS_EXCEPTION;
        String inputAddress = "Av Rio Branco, 1, Centro Rio de Janeiro RJ;" +
                "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ" +
                "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ";


        Exception exception = assertThrows(InsufficientAddressException.class,
                () -> service.mountListAddress(inputAddress));

        assertEquals(expectedMessageReturn, exception.getMessage());
    }

    @Test
    void whenMountListAddressReceiveAddressNotExist() {

        ResponseGeolocationApiDto firstReturn =
                ResponseGeolocationApiDto
                        .builder()
                        .results(List.of())
                        .status("ZERO_RESULTS")
                        .build();

        String expectedMessageReturn = ZERO_RESULTS_MESSAGE;
        String inputAddress = "AAAAAAA;" +
                "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ;" +
                "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ;";

        when(geolocationApi.getGeolocationByAddress(anyString()))
                .thenReturn(firstReturn);

        Exception exception = assertThrows(ZeroResultsAddressException.class,
                () -> service.mountListAddress(inputAddress));

        assertEquals(expectedMessageReturn, exception.getMessage());
    }
    @Test
    void whenReceivingListWithThreeCorrectAddresses() {

        int numberSizeAddressComparable = 3;

        Random random = new Random();
        ResponseGeolocationApiDto firstReturn =
                createResponseGeolocation("Rua das Goianas", random);
        ResponseGeolocationApiDto secondReturn =
                createResponseGeolocation("rua das Aboboras", random);
        ResponseGeolocationApiDto thirdReturn =
                createResponseGeolocation("rua das Mandiocas", random);
        String inputAddress = "Rua das Goiabas;" +
                "Rua das Aboboras;" +
                "Rua das Mandiocas;";

        when(geolocationApi.getGeolocationByAddress(anyString()))
                .thenReturn(firstReturn)
                .thenReturn(secondReturn)
                .thenReturn(thirdReturn);

        ResponseDto responseDto = service.mountListAddress(inputAddress);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(numberSizeAddressComparable,responseDto.getDistanceAddressList().size());
    }
    @Test
    void whenReceivingListWithFourCorrectAddresses() {

        int numberSizeAddressComparable = 6;

        Random random = new Random();
        ResponseGeolocationApiDto firstReturn =
                createResponseGeolocation("Rua das Goianas", random);
        ResponseGeolocationApiDto secondReturn =
                createResponseGeolocation("rua das Aboboras", random);
        ResponseGeolocationApiDto thirdReturn =
                createResponseGeolocation("rua das Mandiocas", random);
        ResponseGeolocationApiDto fourthReturn =
                createResponseGeolocation("rua das Flores", random);
        String inputAddress = "Rua das Goiabas;" +
                "Rua das Aboboras;" +
                "Rua das Mandiocas;" +
                "Rua das Flores;";

        when(geolocationApi.getGeolocationByAddress(anyString()))
                .thenReturn(firstReturn)
                .thenReturn(secondReturn)
                .thenReturn(thirdReturn)
                .thenReturn(fourthReturn);

        ResponseDto responseDto = service.mountListAddress(inputAddress);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(numberSizeAddressComparable,responseDto.getDistanceAddressList().size());
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