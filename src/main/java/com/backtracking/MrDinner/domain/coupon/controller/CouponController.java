package com.backtracking.MrDinner.domain.coupon.controller;


import com.backtracking.MrDinner.domain.Coupon.dto.*;
import com.backtracking.MrDinner.domain.Coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.Coupon.service.CouponService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService CouponService;

    @PostMapping
    public ResponseEntity<CouponCreateResponseDto> createCoupon(@RequestBody CouponCreateRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            CouponService.createCoupon(requestDto, session);
            dtoMetaData = new DtoMetaData("주소 설정 완료");
            return ResponseEntity.ok(new CouponCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CouponCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<CouponFetchResponseDto> fetchCoupon(@RequestBody CouponFetchRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            List<Coupon> CouponList = CouponService.fetchCoupon(requestDto, session);
            dtoMetaData = new DtoMetaData("나의 주소 조회 완료");
            return ResponseEntity.ok(new CouponFetchResponseDto(dtoMetaData, CouponList));
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CouponFetchResponseDto(dtoMetaData));
        }
    }

    @PutMapping
    public ResponseEntity<CouponUpdateResponseDto> updateCoupon(@RequestBody CouponUpdateRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            CouponService.updateCoupon(requestDto, session);
            dtoMetaData = new DtoMetaData("주소 수정 완료");
            return ResponseEntity.ok(new CouponUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CouponUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<CouponDeleteResponseDto> deleteCoupon(@RequestBody CouponDeleteRequestDto requestDto, HttpSession session){

        DtoMetaData dtoMetaData;

        try{
            CouponService.deleteCoupon(requestDto, session);
            dtoMetaData = new DtoMetaData("주소 삭제 완료");
            return ResponseEntity.ok(new CouponDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CouponDeleteResponseDto(dtoMetaData));
        }
    }
}
