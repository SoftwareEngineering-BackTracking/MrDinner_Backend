package com.backtracking.MrDinner.domain.dinner.dto;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerIngredient;
import lombok.Data;

import java.util.Date;

@Data
public class DinnerIngredientFetchRequestDto {
    private DinnerIngredient dinnerIngredient;
}
