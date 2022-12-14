package com.backtracking.MrDinner.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartCouponRepository extends JpaRepository<CartCoupon, Long> {
    CartCoupon findByCartNo(Cart cartNo);

    @Transactional
    void deleteByCartNo(Cart cartNo);
}
