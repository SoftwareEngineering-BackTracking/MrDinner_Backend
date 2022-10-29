package com.backtracking.MrDinner.domain.coupon.service;

import com.backtracking.MrDinner.domain.coupon.dto.CouponCreateRequestDto;
import com.backtracking.MrDinner.domain.coupon.dto.CouponFetchRequestDto;
import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    
    // 테스트를 위한 임시 생성
    @Transactional
    public void createCoupon(CouponCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        couponRepository.save(requestDto.toEntity(id));
    }

    @Transactional
    public List<Coupon> fetchCoupon(CouponFetchRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");

        List<Coupon> couponList = couponRepository.findAllByUserId(id);

        if(couponList.isEmpty()){
            throw new IllegalArgumentException("가지고 있는 쿠폰이 없습니다.");
        }
        return couponList;
    }
}
