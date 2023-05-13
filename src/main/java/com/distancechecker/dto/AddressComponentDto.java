package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class AddressComponentDto {
    @JsonProperty("long_name")
    public String longName;
    @JsonProperty("short_name")
    public String shortName;
    public ArrayList<String> types;
}
