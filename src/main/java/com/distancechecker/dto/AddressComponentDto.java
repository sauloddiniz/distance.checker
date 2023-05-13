package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Builder
public class AddressComponentDto {
    @JsonProperty("long_name")
    public String longName;
    @JsonProperty("short_name")
    public String shortName;
    public List<String> types;
}
