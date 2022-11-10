package com.backtracking.MrDinner.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    List<CartDetail> findByCartItemNo(Long cartItemNo);
    @Transactional
    void deleteAllByCartItemNo(Long cartItemNo);
    List<CartDetail> findAllByCartItemNo(Long cartItemNo);
}
