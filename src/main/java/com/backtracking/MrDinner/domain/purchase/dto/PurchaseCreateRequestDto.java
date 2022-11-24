package com.backtracking.MrDinner.domain.purchase.dto;

import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
import com.backtracking.MrDinner.domain.user.repository.User;
import lombok.Data;

@Data
public class PurchaseCreateRequestDto {
    private String id;
    private String cardNumber;
    private String bank;

    public Purchase toEntity(User userId) {
        return Purchase.builder()
                .userId(userId)
                .cardNumber(cardNumber)
                .bank(bank)
                .build();
    }
}
