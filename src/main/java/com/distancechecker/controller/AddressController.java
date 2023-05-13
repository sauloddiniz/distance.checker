package com.distancechecker.controller;

import com.distancechecker.exceptions.AddressBlankException;
import com.distancechecker.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.distancechecker.UtilValues.FIRST_ELEMENT;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("address")
public class AddressController {

    final
    AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    ResponseEntity<Object> getAddress(@RequestParam(value = "address") String address) {

        if(address.isEmpty() || StringUtils.isEmpty(address)) {
            throw new AddressBlankException();
        }
        return ResponseEntity.ok(addressService.getListAddress(address));
    }

}
