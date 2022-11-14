package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartItem;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerStyle;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Data;

@Data
public class CartItemCreateRequestDto {
    private Dinner dinner;
    private Style style;

    public CartItem toEntity(Cart cartNo, Long price, DinnerList dinnerList, StyleList styleList){
        return CartItem.builder()
                .cartNo(cartNo)
                .dinner(dinnerList)
                .style(styleList)
                .price(price)
                .build();
    }
}
