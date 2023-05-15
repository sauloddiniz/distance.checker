package com.distancechecker.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto{
    public double lat;
    public double lng;
}
