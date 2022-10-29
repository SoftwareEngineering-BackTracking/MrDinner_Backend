package com.backtracking.MrDinner.domain.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAllByUserId(String id);
    Coupon findByCouponNoAndUserId(Long couponNo, String id);
}
