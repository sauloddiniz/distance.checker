package com.distancechecker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.DecimalFormat;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

}
