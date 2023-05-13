package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlusCodeDto{
    @JsonProperty("compound_code")
    public String compoundCode;
    @JsonProperty("global_code")
    public String globalCode;
}
