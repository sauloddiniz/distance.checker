package com.distancechecker.utils;

public class MessageUtils {

    private MessageUtils(){}
    public static final String GENERIC_ADDRESS_EXCEPTION = "Please ensure that the provided address is valid and try again.";
    public static final String ADDRESS_BLANK_EXCEPTION = "Address is required.";
    public static final String INSUFFICIENT_ADDRESS_EXCEPTION = "The number of addresses provided is insufficient. Please provide at least three addresses.";
    public static final String ZERO_RESULTS_MESSAGE = "No results found for the given geolocation request.";
    public static final String ADDRESS_GEOMETRY_IS_NULL = "The address does not provide geometric information.";
    public static final String ADDRESS_FORMATTED_ADDRESS_IS_NULL = "The address does not provide formatted address information.";
    public static final String ADDRESS_GEOLOCATION_NUMBER_IS_NULL = "The address does not include a geolocation number: ";
}
