package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseGeolocationApiDto implements Serializable {
    public List<ResultDto> results;
    public String status;
}