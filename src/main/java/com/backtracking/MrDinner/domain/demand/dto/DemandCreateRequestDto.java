package com.backtracking.MrDinner.domain.demand.dto;

import com.backtracking.MrDinner.domain.demand.repository.Demand;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Data;

@Data
public class DemandCreateRequestDto {

    private Dinner dinner;
    private Style style;

    public Demand toEntity(String userId){
        return Demand.builder()
                .userId(userId)
                .basketNo((long) -1)
                .dinner(dinner)
                .style(style)
                .build();
    }
}
