package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultDto{
    @JsonProperty("address_components")
    private List<AddressComponentDto> addressComponents;
    @JsonProperty("formatted_address")
    private String formattedAddress;
    private GeometryDto geometry;
    @JsonProperty("partial_match")
    private boolean partialMatch;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("plus_code")
    private PlusCodeDto plusCode;
    private List<String> types;
}
