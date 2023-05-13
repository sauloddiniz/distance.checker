package com.distancechecker.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class LocationDto{
    public double lat;
    public double lng;
}
