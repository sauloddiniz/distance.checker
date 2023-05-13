package com.distancechecker.exceptions;

import static com.distancechecker.utils.MessageUtils.INSUFFICIENT_ADDRESS_EXCEPTION;

public class InsufficientAddressException extends RuntimeException {
    public InsufficientAddressException(){
        super(INSUFFICIENT_ADDRESS_EXCEPTION);
    }
}
