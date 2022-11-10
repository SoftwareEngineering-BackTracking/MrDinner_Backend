package com.backtracking.MrDinner.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartNo(Long cartNo);
    @Transactional
    void deleteAllByCartNo(Long cartNo);
    List<CartItem> findAllByCartNo(Long cartNo);
}
