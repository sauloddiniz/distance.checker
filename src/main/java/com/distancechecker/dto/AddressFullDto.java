package com.distancechecker.dto;

import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressFullDto {
    private Double differenceDistanceKm;
    private String addressA;
    private String addressB;

    public AddressFullDto(AddressComparableDto addressComparableDto) {
        this.differenceDistanceKm = addressComparableDto.getDifferenceDistanceKm();
        this.addressA = addressComparableDto.getAddressA();
        this.addressB = addressComparableDto.getAddressB();
    }

}
