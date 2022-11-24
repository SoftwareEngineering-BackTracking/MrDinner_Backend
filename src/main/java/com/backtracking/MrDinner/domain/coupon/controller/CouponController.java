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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public ResponseEntity<CouponCreateResponseDto> createCoupon(@RequestBody CouponCreateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            couponService.createCoupon(requestDto);
            dtoMetaData = new DtoMetaData("쿠폰 생성 완료");
            return ResponseEntity.ok(new CouponCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CouponCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<CouponFetchResponseDto> fetchCoupon(@RequestHeader Map<String, Object> params){
        DtoMetaData dtoMetaData;
        CouponFetchRequestDto requestDto = new CouponFetchRequestDto((String) params.get("id"),(Long) params.get("couponNo"));
        try{
            if(requestDto.getCouponNo() == null){
                List<Coupon> CouponList = couponService.fetchMyCoupon(requestDto);
                dtoMetaData = new DtoMetaData("나의 쿠폰 조회 완료");
                return ResponseEntity.ok(new CouponFetchResponseDto(dtoMetaData, CouponList));
            }
            else{
                List<Coupon> CouponList = couponService.fetchCoupon(requestDto);
                dtoMetaData = new DtoMetaData("특정 쿠폰 조회 완료");
                return ResponseEntity.ok(new CouponFetchResponseDto(dtoMetaData, CouponList));
            }
        }
        catch (Exception e){
            dtoMetaData= new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CouponFetchResponseDto(dtoMetaData));
        }
    }


}
