package com.backtracking.MrDinner.domain.dinner.dto;

import com.backtracking.MrDinner.global.enumpackage.DinnerIngredient;
import lombok.Data;

import java.util.Date;

@Data
public class DinnerIngredientUpdateRequestDto {

    private DinnerIngredient dinnerIngredient;
    private Long quantity;
    private Date demandDate;
}
