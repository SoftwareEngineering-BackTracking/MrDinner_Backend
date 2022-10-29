package com.backtracking.MrDinner.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartCouponRepository extends JpaRepository<CartCoupon, Long> {
    Long findByCartNo(Long cartNo);

    void deleteByCartNo(Long cartNo);
}
