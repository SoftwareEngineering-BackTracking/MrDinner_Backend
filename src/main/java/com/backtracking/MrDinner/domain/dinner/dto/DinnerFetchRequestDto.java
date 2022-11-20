package com.backtracking.MrDinner.domain.dinner.dto;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DinnerFetchRequestDto {
    private Dinner dinner;
}
