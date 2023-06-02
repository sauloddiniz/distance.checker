package com.distancechecker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
   private AddressFullDto nearestAddress ;
   private AddressFullDto farthestAddress;
   private List<AddressComparableDto> distanceAddressList;
}
