package com.backtracking.MrDinner.domain.coupon.controller;


import com.backtracking.MrDinner.domain.coupon.dto.CouponCreateRequestDto;
import com.backtracking.MrDinner.domain.coupon.dto.CouponCreateResponseDto;
import com.backtracking.MrDinner.domain.coupon.dto.CouponFetchRequestDto;
import com.backtracking.MrDinner.domain.coupon.dto.CouponFetchResponseDto;
import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.coupon.service.CouponService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<CouponCreateResponseDto> createCoupon(@RequestBody CouponCreateRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            couponService.createCoupon(requestDto, session);
            dtoMetaData = new DtoMetaData("쿠폰 생성 완료");
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
            List<Coupon> CouponList = couponService.fetchCoupon(requestDto, session);
            dtoMetaData = new DtoMetaData("나의 쿠폰 조회 완료");
            return ResponseEntity.ok(new CouponFetchResponseDto(dtoMetaData, CouponList));
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CouponFetchResponseDto(dtoMetaData));
        }
    }


}
