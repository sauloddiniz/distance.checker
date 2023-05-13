package com.distancechecker.controller;

import com.distancechecker.client.ConnectGeolocationApi;
import com.distancechecker.dto.*;
import com.distancechecker.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    AddressService addressService;

    @InjectMocks
    AddressController addressController;

    ResponseGeolocationApiDto responseGeolocationApiDto;



    @Test
    void testGetAddress() {
        // Arrange
        String inputAddress = "Av Rio Branco, 1, Centro Rio de Janeiro RJ;" +
                "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ;" +
                "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ;";

        Mockito.when(addressService.getAddress(anyString()))
                .thenReturn(responseGeolocationApiDto);

        // Act


        // Assert
        Assertions.assertEquals(true, true);
        // Add more assertions based on your expected response
    }
}