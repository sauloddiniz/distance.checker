package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GeometryDto{

    private LocationDto location;
    private BoundsDto bounds;
    @JsonProperty("location_type")
    private String locationType;
    private ViewportDto viewport;
}
