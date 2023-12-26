package com.distancechecker.controller;

import com.distancechecker.controller.documentation.AddressApiDocumentation;
import com.distancechecker.dto.ResponseDto;
import com.distancechecker.dto.error.ErrorResponseDto;
import com.distancechecker.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("address")
public class AddressController implements AddressApiDocumentation {

    final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public ResponseEntity<ResponseDto> getAddress(String address) {
        return ResponseEntity.ok(addressService.mountListAddress(address));
    }
}
