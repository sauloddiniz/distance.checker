package com.distancechecker.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ViewportDto{
    private NortheastDto northeast;
    private SouthwestDto southwest;
}
