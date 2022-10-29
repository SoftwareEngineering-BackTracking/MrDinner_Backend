package com.backtracking.MrDinner.domain.cart.controller;

import com.backtracking.MrDinner.domain.cart.dto.CartDetailCreateRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.CartDetailCreateResponseDto;
import com.backtracking.MrDinner.domain.cart.dto.PaymentRequestDto;
import com.backtracking.MrDinner.domain.cart.dto.PaymentResponseDto;
import com.backtracking.MrDinner.domain.cart.service.PaymentService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/cart/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    @PostMapping
    public ResponseEntity<PaymentResponseDto> payCart(@RequestBody PaymentRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            paymentService.payCart(requestDto, session);
            dtoMetaData = new DtoMetaData("결제 성공");
            return ResponseEntity.ok(new PaymentResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PaymentResponseDto(dtoMetaData));
        }
    }
}
