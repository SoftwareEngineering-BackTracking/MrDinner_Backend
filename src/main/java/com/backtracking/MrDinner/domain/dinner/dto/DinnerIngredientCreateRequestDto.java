package com.backtracking.MrDinner.domain.dinner.dto;

import com.backtracking.MrDinner.domain.dinner.repository.DinnerIngredientList;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerIngredient;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DinnerIngredientCreateRequestDto {
    private DinnerIngredient dinnerIngredient;
    private Dinner dinner;
    private Long price;
    private Long quantity;
    private Date demandDate;

    public DinnerIngredientList toEntity(DinnerIngredient dinnerIngredient, DinnerList dinnerList, Long price, Long quantity, Date demandDate) {
        return DinnerIngredientList.builder()
                .dinnerIngredient(dinnerIngredient)
                .dinner(dinnerList)
                .price(price)
                .quantity(quantity)
                .demandDate(demandDate)
                .build();
    }
}
