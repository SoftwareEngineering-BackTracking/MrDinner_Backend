package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import lombok.Data;

import java.util.List;

@Data
public class CartDetailFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<CartDetail> cartDetails;

    public CartDetailFetchResponseDto(DtoMetaData dtoMetaData, List<CartDetail> cartDetails){
        this.dtoMetaData = dtoMetaData;
        this.cartDetails = cartDetails;
    }

    public CartDetailFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
