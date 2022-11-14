package com.backtracking.MrDinner.domain.address.repository;

import com.backtracking.MrDinner.domain.user.repository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByDetailAndUserId(String detail, User userId);
    List<Address> findAllByUserId(User id);
    Address findAllByAddressNoAndUserId(Long addressNo, User id);
}
