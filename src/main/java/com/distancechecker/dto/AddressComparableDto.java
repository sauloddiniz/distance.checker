package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
