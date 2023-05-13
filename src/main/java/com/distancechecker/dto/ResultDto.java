package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class ResultDto{
    @JsonProperty("address_components")
    public ArrayList<AddressComponentDto> addressComponents;
    @JsonProperty("formatted_address")
    public String formattedAddress;
    public GeometryDto geometry;
    @JsonProperty("partial_match")
    public boolean partialMatch;
    @JsonProperty("place_id")
    public String placeId;
    @JsonProperty("plus_code")
    public PlusCodeDto plusCode;
    public ArrayList<String> types;
}
