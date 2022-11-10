package com.backtracking.MrDinner.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(String id);
    boolean existsByUserId(String id);
}
