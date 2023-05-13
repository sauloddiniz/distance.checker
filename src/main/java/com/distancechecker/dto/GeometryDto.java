package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GeometryDto{

    private LocationDto location;
    private BoundsDto bounds;
    @JsonProperty("location_type")
    private String locationType;
    private ViewportDto viewport;
}
