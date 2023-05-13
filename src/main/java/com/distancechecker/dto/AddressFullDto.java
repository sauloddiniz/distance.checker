package com.distancechecker.dto;

import lombok.*;

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
