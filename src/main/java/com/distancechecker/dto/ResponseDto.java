package com.distancechecker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto implements Serializable {
    AddressFullDto nearestAddress;
    AddressFullDto farthestAddress;
    List<AddressComparableDto> distanceAddressList;
}
