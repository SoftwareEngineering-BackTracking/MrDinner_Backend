package com.backtracking.MrDinner.domain.user.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor {
    boolean existsByEmail(@Param("email") String email);
    User findByEmail(@Param("email") String email);
}
