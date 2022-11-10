package com.backtracking.MrDinner.domain.style.dto;

import com.backtracking.MrDinner.domain.style.repository.StyleIngredientList;
import com.backtracking.MrDinner.global.enumpackage.Style;
import com.backtracking.MrDinner.global.enumpackage.StyleIngredient;
import lombok.Data;

import java.util.Date;

@Data
public class StyleIngredientCreateRequestDto {

    private StyleIngredient styleIngredient;
    private Style style;
    private Long price;
    private Long quantity;
    private Date demandDate;

    public StyleIngredientList toEntity(StyleIngredient styleIngredient, Style style, Long price, Long quantity, Date demandDate){
        return StyleIngredientList.builder()
                .styleIngredient(styleIngredient)
                .style(style)
                .price(price)
                .quantity(quantity)
                .demandDate(demandDate)
                .build();
    }
}
