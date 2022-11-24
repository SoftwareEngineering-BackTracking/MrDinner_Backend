package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.domain.user.repository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(User id);
    boolean existsByUserId(User user);
}
