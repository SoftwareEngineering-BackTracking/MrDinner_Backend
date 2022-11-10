package com.backtracking.MrDinner.domain.cart.controller;

import com.backtracking.MrDinner.domain.cart.dto.*;
import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import com.backtracking.MrDinner.domain.cart.repository.CartDetail;
import com.backtracking.MrDinner.domain.cart.service.CartDetailService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/cartdetail")
@RequiredArgsConstructor
public class CartDetailController {

    private final CartDetailService cartDetailService;

    @PostMapping
    public ResponseEntity<CartDetailCreateResponseDto> createcartDetail(@RequestBody CartDetailCreateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            cartDetailService.createCartDetail(requestDto);
            dtoMetaData = new DtoMetaData("주문 세부사항 생성 성공");
            return ResponseEntity.ok(new CartDetailCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CartDetailCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<CartDetailFetchResponseDto> fetchCartDetail(@RequestBody CartDetailFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            List<CartDetail> cartDetailList = cartDetailService.fetchCartDetail(requestDto);
            dtoMetaData = new DtoMetaData("주문 세부사항 조회 성공");
            return ResponseEntity.ok(new CartDetailFetchResponseDto(dtoMetaData, cartDetailList));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CartDetailFetchResponseDto(dtoMetaData));
        }
    }

    @PutMapping
    public ResponseEntity<CartDetailUpdateResponseDto> updateCartDetail(@RequestBody CartDetailUpdateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            cartDetailService.updateCartDetail(requestDto);
            dtoMetaData = new DtoMetaData("주문 세부사항 수정 성공");
            return ResponseEntity.ok(new CartDetailUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CartDetailUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<CartDetailDeleteResponseDto> deleteCartDetail(@RequestBody CartDetailDeleteRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            cartDetailService.deleteCartDetail(requestDto);
            dtoMetaData = new DtoMetaData("세부사항 삭제 성공");
            return ResponseEntity.ok(new CartDetailDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CartDetailDeleteResponseDto(dtoMetaData));
        }
    }
}

