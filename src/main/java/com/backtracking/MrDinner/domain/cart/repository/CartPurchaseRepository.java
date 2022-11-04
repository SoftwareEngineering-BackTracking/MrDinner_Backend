package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.domain.cart.repository.CartPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartPurchaseRepository extends JpaRepository<CartPurchase, Long> {
    CartPurchase findByCartNo(Long cartNo);
    @Transactional
    void deleteByCartNo(Long cartNo);
}
