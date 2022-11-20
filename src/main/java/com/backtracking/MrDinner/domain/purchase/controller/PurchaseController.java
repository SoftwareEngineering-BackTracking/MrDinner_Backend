package com.backtracking.MrDinner.domain.purchase.controller;


import com.backtracking.MrDinner.domain.purchase.dto.*;
import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
import com.backtracking.MrDinner.domain.purchase.service.PurchaseService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseCreateResponseDto> createPurchase(@RequestBody PurchaseCreateRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            purchaseService.createPurchase(requestDto, session);
            dtoMetaData = new DtoMetaData("결제 정보 생성 완료");
            return ResponseEntity.ok(new PurchaseCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PurchaseCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<PurchaseFetchResponseDto> fetchPurchase(@RequestHeader Map<String, Long> params, HttpSession session){
        DtoMetaData dtoMetaData;
        PurchaseFetchRequestDto requestDto = new PurchaseFetchRequestDto(params.get("purchaseNo"));
        try{
            if(requestDto.getPurchaseNo() == null){
                List<Purchase> PurchaseList = purchaseService.fetchMyPurchase(requestDto, session);
                dtoMetaData = new DtoMetaData("나의 결제 정보 조회 완료");
                return ResponseEntity.ok(new PurchaseFetchResponseDto(dtoMetaData, PurchaseList));
            }
            else{
                List<Purchase> PurchaseList = purchaseService.fetchPurchase(requestDto, session);
                dtoMetaData = new DtoMetaData("특정 결제 정보 조회 완료");
                return ResponseEntity.ok(new PurchaseFetchResponseDto(dtoMetaData, PurchaseList));
            }
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PurchaseFetchResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<PurchaseDeleteResponseDto> deletePurchase(@RequestBody PurchaseDeleteRequestDto requestDto, HttpSession session){

        DtoMetaData dtoMetaData;

        try{
            purchaseService.deletePurchase(requestDto, session);
            dtoMetaData = new DtoMetaData("결제 정보 삭제 완료");
            return ResponseEntity.ok(new PurchaseDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PurchaseDeleteResponseDto(dtoMetaData));
        }
    }
}
