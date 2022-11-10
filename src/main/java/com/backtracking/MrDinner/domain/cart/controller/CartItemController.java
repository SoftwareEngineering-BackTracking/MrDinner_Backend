package com.backtracking.MrDinner.domain.cart.controller;

import com.backtracking.MrDinner.domain.cart.dto.*;
import com.backtracking.MrDinner.domain.cart.repository.CartItem;
import com.backtracking.MrDinner.domain.cart.service.CartItemService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/cartitem")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<CartItemCreateResponseDto> createCartItem(@RequestBody CartItemCreateRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            cartItemService.createCartItem(requestDto, session);
            dtoMetaData = new DtoMetaData("장바구니 중 단일 주문 생성 성공");
            return ResponseEntity.ok(new CartItemCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CartItemCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<CartItemFetchResponseDto> fetchCartItem(@RequestBody CartItemFetchRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            List<CartItem> cartItemList = cartItemService.fetchCartItem(requestDto, session);
            dtoMetaData = new DtoMetaData("장바구니에 담긴 모든 주문 조회 성공");
            return ResponseEntity.ok(new CartItemFetchResponseDto(dtoMetaData, cartItemList));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CartItemFetchResponseDto(dtoMetaData));
        }
    }

    @PutMapping
    public ResponseEntity<CartItemUpdateResponseDto> updateCartItem(@RequestBody CartItemUpdateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            cartItemService.updateCartItem(requestDto);
            dtoMetaData = new DtoMetaData("장바구니에 담긴 주문 수정 성공");
            return ResponseEntity.ok(new CartItemUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CartItemUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<CartItemDeleteResponseDto> deleteCartItem(@RequestBody CartItemDeleteRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            cartItemService.deleteCartItem(requestDto);
            dtoMetaData = new DtoMetaData("장바구니에 담긴 주문 삭제 성공");
            return ResponseEntity.ok(new CartItemDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CartItemDeleteResponseDto(dtoMetaData));
        }
    }
}
