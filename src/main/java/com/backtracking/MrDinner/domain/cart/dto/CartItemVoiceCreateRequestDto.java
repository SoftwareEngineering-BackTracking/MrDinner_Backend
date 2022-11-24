package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartItem;
import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CartItemVoiceCreateRequestDto {
    private String id;
    private MultipartFile audioFile;

    public CartItem toEntity(Cart cartNo, Long price, DinnerList dinnerList, StyleList styleList){
        return CartItem.builder()
                .cartNo(cartNo)
                .dinner(dinnerList)
                .style(styleList)
                .price(price)
                .build();
    }
}
