package com.backtracking.MrDinner.domain.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartAddressRepository extends JpaRepository<CartAddress, Long> {
    Long findByCartNo(Long cartNo);
    void deleteByCartNo(Long cartNo);
}
