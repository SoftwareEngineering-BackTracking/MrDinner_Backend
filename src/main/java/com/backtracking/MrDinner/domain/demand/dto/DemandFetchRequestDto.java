package com.backtracking.MrDinner.domain.demand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DemandFetchRequestDto {
    private String filter;
    // null = 전체 조회
    // userId = 회원만 조회
}
