package com.distancechecker.controller;

import com.distancechecker.dto.ResponseDto;
import com.distancechecker.dto.error.ErrorResponseDto;
import com.distancechecker.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressController {

    final
    AddressService addressService;

    @GetMapping
    @Operation(
            summary = "Compare the distance between the addresses",
            description = "Please enter a minimum of three addresses, separated by the semicolon character ( ; )."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class))})
    })

    ResponseEntity<ResponseDto> getAddress(@RequestParam(value = "address",
            defaultValue = "Av Rio Branco, 1, Centro Rio de Janeiro RJ;" +
                            "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ;" +
                            "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ;") String address) {
        return ResponseEntity.ok(addressService.mountListAddress(address));
    }

}
