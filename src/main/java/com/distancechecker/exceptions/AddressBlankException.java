package com.distancechecker.exceptions;

import static com.distancechecker.utils.MessageUtils.ADDRESS_BLANK_EXCEPTION;

public class AddressBlankException extends RuntimeException {
    public AddressBlankException() {
        super(ADDRESS_BLANK_EXCEPTION);
    }
}
