package com.distancechecker.exceptions;

import static com.distancechecker.utils.MessageUtils.ADDRESS_FORMATTED_ADDRESS_IS_NULL;

public class FormattedAddressNullException extends RuntimeException {
    public FormattedAddressNullException() {
        super(ADDRESS_FORMATTED_ADDRESS_IS_NULL);
    }
}
