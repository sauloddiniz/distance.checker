package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.text.DecimalFormat;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressComparableDto implements Comparable<AddressComparableDto>{
    private Double differenceDistanceKm;
    @JsonIgnore
    private String addressA;
    @JsonIgnore
    private String addressB;

    @Override
    public int compareTo(AddressComparableDto addressComparable) {
        return Double.compare(this.differenceDistanceKm, addressComparable.differenceDistanceKm);
    }

    public AddressComparableDto(Double differenceDistanceKm) {
        this.differenceDistanceKm = differenceDistanceKm;
    }
}
