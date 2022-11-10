package com.backtracking.MrDinner.domain.coupon.dto;

import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.Data;

import java.util.List;

@Data
public class CouponFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<Coupon> couponList;
    public CouponFetchResponseDto(DtoMetaData dtoMetaData, List<Coupon> couponList){
        this.dtoMetaData = dtoMetaData;
        this.couponList = couponList;
    }

    public CouponFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
