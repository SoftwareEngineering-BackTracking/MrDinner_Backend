package com.backtracking.MrDinner.domain.address.dto;

import lombok.Data;

@Data
public class AddressUpdateRequestDto {
    private String id;
    private Long addressNo;
    private String detail;
}
