package com.distancechecker.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto{
    private double lat;
    private double lng;
}
