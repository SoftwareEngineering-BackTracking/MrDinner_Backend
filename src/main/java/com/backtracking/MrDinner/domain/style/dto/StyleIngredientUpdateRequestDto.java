package com.backtracking.MrDinner.domain.style.dto;

import com.backtracking.MrDinner.global.enumpackage.StyleIngredient;
import lombok.Data;

import javax.swing.text.Style;
import java.util.Date;

@Data
public class StyleIngredientUpdateRequestDto {

    private StyleIngredient styleIngredient;
    private Style style;
    private Long price;
    private Long quantity;
    private Date demandDate;
}
