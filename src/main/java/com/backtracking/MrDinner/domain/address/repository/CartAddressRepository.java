package com.backtracking.MrDinner.domain.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartAddressRepository extends JpaRepository<CartAddress, Long> {
    CartAddress findByCartNo(Long cartNo);
    @Transactional
    void deleteByCartNo(Long cartNo);
}
