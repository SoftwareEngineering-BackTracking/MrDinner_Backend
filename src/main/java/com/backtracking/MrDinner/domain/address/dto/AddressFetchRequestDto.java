package com.backtracking.MrDinner.domain.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressFetchRequestDto {
    private String id;
    private Long addressNo;
}
