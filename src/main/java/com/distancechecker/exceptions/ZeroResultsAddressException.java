package com.distancechecker.exceptions;

import static com.distancechecker.utils.MessageUtils.ZERO_RESULTS_MESSAGE;

public class ZeroResultsAddressException extends RuntimeException {
    public ZeroResultsAddressException(){
        super(ZERO_RESULTS_MESSAGE);
    }
}
