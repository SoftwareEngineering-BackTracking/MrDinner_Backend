package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import com.backtracking.MrDinner.domain.cart.repository.CartItem;
import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import lombok.Data;

import java.util.List;

@Data
public class CartDetailCreateRequestDto {
    private Long cartItemNo;
    private List<String> name;
    private List<DetailStatus> status;


    public CartDetail toEntity(int index){
        return CartDetail.builder()
                .cartItemNo(cartItemNo)
                .name(name.get(index))
                .status(status.get(index))
                .build();
    }

}
