package com.distancechecker.controller;

import com.distancechecker.dto.AddressComparableDto;
import com.distancechecker.dto.AddressFullDto;
import com.distancechecker.dto.ResponseDto;
import com.distancechecker.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    AddressService addressService;

    @InjectMocks
    private AddressController addressController;
    @Test
    void whenResponseOk() {

        int expectedOfTheNumberOfComparisons = 3;

        String address = "Av Rio Branco, 1, Centro Rio de Janeiro RJ;" +
                "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ;" +
                "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ;";

        AddressFullDto expectedNearestAddress = new AddressFullDto(1.254177620524974,
                "Av. Rio Branco, 1 - Centro, Rio de Janeiro - RJ, 20090-003, Brazil",
                "Praça Mal. Âncora, 122 - Centro, Rio de Janeiro - RJ, 20021-200, Brazil");
        AddressFullDto expectedFarthestAddress = new AddressFullDto(5.980923772140222,
                "Av. Rio Branco, 1 - Centro, Rio de Janeiro - RJ, 20090-003, Brazil",
                "R. Dezenove de Fevereiro, 34 - Botafogo, Rio de Janeiro - RJ, 22280-030, Brazil");

        ResponseDto expectedAddresses =
                new ResponseDto(expectedNearestAddress,
                        expectedNearestAddress,
                        List.of(
                                new AddressComparableDto(1.254177620524974),
                                new AddressComparableDto( 5.492266461044177),
                                new AddressComparableDto(5.980923772140222)
                        ));

        when(addressService.mountListAddress(address)).thenReturn(expectedAddresses);

        ResponseEntity<ResponseDto> response = addressController.getAddress(address);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expectedAddresses, response.getBody());
        Assertions.assertEquals(expectedOfTheNumberOfComparisons, response.getBody().getDistanceAddressList().size());
    }

}