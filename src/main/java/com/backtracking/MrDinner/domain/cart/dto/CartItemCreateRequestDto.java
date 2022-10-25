package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartItem;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Data;

@Data
public class CartItemCreateRequestDto {
    private Dinner dinner;
    private Style style;

    public CartItem toEntity(Long cartNo){
        return CartItem.builder()
                .cartNo(cartNo)
                .dinner(dinner)
                .style(style)
                .build();
    }
}
