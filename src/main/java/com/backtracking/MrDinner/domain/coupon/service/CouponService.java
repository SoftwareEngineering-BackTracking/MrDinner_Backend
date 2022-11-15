package com.backtracking.MrDinner.domain.coupon.service;

import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.domain.coupon.dto.CouponCreateRequestDto;
import com.backtracking.MrDinner.domain.coupon.dto.CouponFetchRequestDto;
import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.coupon.repository.CouponRepository;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    
    // 테스트를 위한 임시 생성
    @Transactional
    public void createCoupon(CouponCreateRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        couponRepository.save(requestDto.toEntity(user));
    }

    @Transactional
    public List<Coupon> fetchMyCoupon(CouponFetchRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        List<Coupon> couponList = couponRepository.findAllByUserId(user);

        if(couponList.isEmpty()){
            throw new IllegalArgumentException("가지고 있는 쿠폰이 없습니다.");
        }
        return couponList;
    }

    @Transactional
    public List<Coupon> fetchCoupon(CouponFetchRequestDto requestDto) {
        Coupon coupon = couponRepository.findById(requestDto.getCouponNo()).orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 없습니다."));

        List<Coupon> couponList = new ArrayList<>();
        couponList.add(coupon);

        return couponList;
    }
}
