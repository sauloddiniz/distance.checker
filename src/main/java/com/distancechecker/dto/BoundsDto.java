package com.distancechecker.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BoundsDto {
    private NortheastDto northeast;
    private SouthwestDto southwest;
}
