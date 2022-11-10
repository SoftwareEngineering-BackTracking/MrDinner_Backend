package com.backtracking.MrDinner.domain.purchase.dto;

import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
import lombok.Data;

@Data
public class PurchaseCreateRequestDto {

    private String cardNumber;
    private String bank;

    public Purchase toEntity(String userId) {
        return Purchase.builder()
                .userId(userId)
                .cardNumber(cardNumber)
                .bank(bank)
                .build();
    }
}
