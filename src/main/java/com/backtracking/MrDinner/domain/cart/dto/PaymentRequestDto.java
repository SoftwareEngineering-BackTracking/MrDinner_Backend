package com.backtracking.MrDinner.domain.cart.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private String id;
    private Long couponNo;
    private Long purchaseNo;
}
