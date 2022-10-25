package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.domain.cart.repository.Cart;
import com.backtracking.MrDinner.domain.cart.repository.CartItem;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class CartItemFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<CartItem> cartItems;

    public CartItemFetchResponseDto(DtoMetaData dtoMetaData, List<CartItem> cartItems){
        this.dtoMetaData = dtoMetaData;
        this.cartItems = cartItems;
    }

    public CartItemFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
