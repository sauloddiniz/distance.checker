package com.distancechecker.exceptions;

import static com.distancechecker.utils.MessageUtils.GENERIC_ADDRESS_EXCEPTION;

public class GenericAddressException extends RuntimeException {
    public GenericAddressException() {
        super(GENERIC_ADDRESS_EXCEPTION);
    }
}
