package com.backtracking.MrDinner.domain.address.repository;

import com.backtracking.MrDinner.domain.cart.repository.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartAddressRepository extends JpaRepository<CartAddress, Long> {
    CartAddress findByCartNo(Cart cartNo);
    @Transactional
    void deleteByCartNo(Cart cartNo);
}
