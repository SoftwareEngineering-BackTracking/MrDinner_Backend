package com.backtracking.MrDinner.domain.style.dto;

import com.backtracking.MrDinner.global.enumpackage.StyleIngredient;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StyleIngredientFetchRequestDto {
    private StyleIngredient styleIngredient;
}
