package com.distancechecker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressComparableDto {
    private Double differenceDistanceKm;
    private String addressA;
    private String addressB;
}
