package com.backtracking.MrDinner.domain.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseFetchRequestDto {
    private String id;
    private Long purchaseNo;
}
