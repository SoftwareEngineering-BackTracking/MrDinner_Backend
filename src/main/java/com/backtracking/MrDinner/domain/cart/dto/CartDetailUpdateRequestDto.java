package com.backtracking.MrDinner.domain.cart.dto;

import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import lombok.Data;

import java.util.List;

@Data
public class CartDetailUpdateRequestDto {
    private Long cartItemNo;
    private List<String> name;
    private List<DetailStatus> status;
}
