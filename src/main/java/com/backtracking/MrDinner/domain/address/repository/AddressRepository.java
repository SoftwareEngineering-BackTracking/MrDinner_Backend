package com.backtracking.MrDinner.domain.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByDetailAndUserId(String detail, String userId);
    List<Address> findAllByUserId(String id);
    Address findAllByAddressNoAndUserId(Long addressNo, String id);
}
