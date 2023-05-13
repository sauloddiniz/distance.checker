package com.distancechecker.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ViewportDto{
    public NortheastDto northeast;
    public SouthwestDto southwest;
}
