package com.backtracking.MrDinner.domain.order.controller;

import com.backtracking.MrDinner.domain.order.dot.OrderCreateRequestDto;
import com.backtracking.MrDinner.domain.order.dto.OrderCreateRequestDto;
import com.backtracking.MrDinner.domain.order.dto.OrderCreateResponseDto;
import com.backtracking.MrDinner.domain.order.service.OrderService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreateRequestDto> createOrder(@RequestBody OrderCreateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            orderService.createOrder(requestDto, )
        }
    }
}
