package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Data;

@Data
public class CartItemUpdateRequestDto {
    private Long cartItemNo;
    private Dinner dinner;
    private Style style;
}
