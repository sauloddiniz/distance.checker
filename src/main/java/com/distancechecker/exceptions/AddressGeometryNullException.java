package com.distancechecker.exceptions;

import static com.distancechecker.utils.MessageUtils.ADDRESS_GEOMETRY_IS_NULL;

public class AddressGeometryNullException extends RuntimeException {
    public AddressGeometryNullException(){
        super(ADDRESS_GEOMETRY_IS_NULL);
    }
}
