package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseGeolocationApiDto implements Serializable {
    public ArrayList<ResultDto> results;
    public String status;
}