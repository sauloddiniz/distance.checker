package com.distancechecker.exceptions;

public class AddressBlankException extends RuntimeException {
    public AddressBlankException() {
        super("Address is required");
    }
}
