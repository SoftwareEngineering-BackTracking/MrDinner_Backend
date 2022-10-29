package com.backtracking.MrDinner.domain.purchase.dto;

import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<Purchase> purchaseList;

    public PurchaseFetchResponseDto(DtoMetaData dtoMetaData, List<Purchase> purchaseList){
        this.dtoMetaData = dtoMetaData;
        this.purchaseList = purchaseList;
    }

    public PurchaseFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
