package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultDto{
    @JsonProperty("address_components")
    public List<AddressComponentDto> addressComponents;
    @JsonProperty("formatted_address")
    public String formattedAddress;
    public GeometryDto geometry;
    @JsonProperty("partial_match")
    public boolean partialMatch;
    @JsonProperty("place_id")
    public String placeId;
    @JsonProperty("plus_code")
    public PlusCodeDto plusCode;
    public List<String> types;
}
