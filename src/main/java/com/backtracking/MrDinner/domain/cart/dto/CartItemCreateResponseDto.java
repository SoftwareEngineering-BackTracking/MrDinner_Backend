package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.global.dto.DtoMetaData;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemCreateResponseDto {
    private DtoMetaData dtoMetaData;

}
