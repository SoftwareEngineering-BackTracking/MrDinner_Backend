package com.backtracking.MrDinner.domain.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CouponFetchRequestDto {
    private String id;
    private Long couponNo;
}
