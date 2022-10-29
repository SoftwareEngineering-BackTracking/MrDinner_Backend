package com.backtracking.MrDinner.domain.cart.dto;

import lombok.Data;

@Data
public class PaymentRequestDto {
    private Long couponNo;
    private Long purchaseNo;
}
