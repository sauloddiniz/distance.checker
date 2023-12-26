package com.distancechecker.controller.documentation;

import com.distancechecker.dto.ResponseDto;
import com.distancechecker.dto.error.ErrorResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface AddressApiDocumentation {
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
    @GetMapping
    ResponseEntity<ResponseDto> getAddress(@RequestParam(value = "address",
            defaultValue = "Av Rio Branco, 1, Centro Rio de Janeiro RJ;" +
                    "Praça Mal. Âncora, 122, Centro, Rio de Janeiro RJ;" +
                    "Rua 19 de Fevereiro, 34, Botafogo, Rio de Janeiro RJ;") String address);
}
