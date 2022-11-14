package com.backtracking.MrDinner.domain.coupon.repository;

import com.backtracking.MrDinner.domain.user.repository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAllByUserId(User id);
    Coupon findByCouponNoAndUserId(Long couponNo, User id);
}
