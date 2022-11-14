package com.backtracking.MrDinner.domain.coupon.dto;

import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.user.repository.User;
import lombok.Data;

import java.util.Date;

@Data
public class CouponCreateRequestDto {
    private Date startTime;
    private Date endTime;
    private String name;
    private Long price;

    public Coupon toEntity(User userId){
        return Coupon.builder()
                .userId(userId)
                .startTime(startTime)
                .endTime(endTime)
                .name(name)
                .price(price)
                .build();
    }
}
