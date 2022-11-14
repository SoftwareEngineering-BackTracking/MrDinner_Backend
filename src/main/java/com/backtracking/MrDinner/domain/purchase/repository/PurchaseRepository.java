package com.backtracking.MrDinner.domain.purchase.repository;

import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.user.repository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUserId(User id);

    Purchase findAllByPurchaseNoAndUserId(Long purchaseNo, User id);
}
