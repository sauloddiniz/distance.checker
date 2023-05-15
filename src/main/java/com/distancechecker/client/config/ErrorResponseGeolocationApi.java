package com.distancechecker.client.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ErrorResponseGeolocationApi {
    @JsonProperty("error_message")
    private String errorMessage;
    private List<String> results;
    private String status;
}
