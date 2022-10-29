package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.domain.cart.repository.CartPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartPurchaseRepository extends JpaRepository<CartPurchase, Long> {
    Long findByCartNo(Long cartNo);

    void deleteByCartNo(Long cartNo);
}
