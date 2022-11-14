package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import com.backtracking.MrDinner.domain.cart.repository.CartItem;
import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerStyle;
import lombok.Data;

import java.util.List;

@Data
public class CartDetailCreateRequestDto {
    private Long cartItemNo;
    private List<String> name;
    private List<DetailStatus> status;
    private List<DinnerStyle> dinnerStyles;


    public CartDetail toEntity(CartItem cartItem, int index, Long price){
        return CartDetail.builder()
                .cartItemNo(cartItem)
                .name(name.get(index))
                .status(status.get(index))
                .price(price)
                .dinnerStyle(dinnerStyles.get(index))
                .build();
    }

}
