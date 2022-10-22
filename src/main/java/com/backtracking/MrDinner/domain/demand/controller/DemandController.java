package com.backtracking.MrDinner.domain.demand.controller;

import com.backtracking.MrDinner.domain.demand.dto.DemandCreateRequestDto;
import com.backtracking.MrDinner.domain.demand.dto.DemandCreateResponseDto;
import com.backtracking.MrDinner.domain.demand.service.DemandService;
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
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class DemandController {
    private final DemandService demandService;

    @PostMapping
    public ResponseEntity<DemandCreateResponseDto> createOrder(@RequestBody DemandCreateRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            demandService.createDemand(requestDto, session);
            dtoMetaData = new DtoMetaData("주문 생성 완료");
            return ResponseEntity.ok(new DemandCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DemandCreateResponseDto(dtoMetaData));
        }
    }
}
