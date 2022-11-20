package com.backtracking.MrDinner.domain.dinner.dto;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerIngredient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DinnerIngredientFetchRequestDto {
    private DinnerIngredient dinnerIngredient;
}
