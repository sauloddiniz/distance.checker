package com.distancechecker.exceptions;

import static com.distancechecker.utils.MessageUtils.ADDRESS_GEOLOCATION_NUMBER_IS_NULL;

public class AddressGeolocationNullException extends RuntimeException {
    public AddressGeolocationNullException(String latOrLong) {
        super(ADDRESS_GEOLOCATION_NUMBER_IS_NULL + " " + latOrLong);
    }
}
