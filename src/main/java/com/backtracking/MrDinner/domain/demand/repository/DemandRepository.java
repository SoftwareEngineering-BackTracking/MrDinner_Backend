package com.backtracking.MrDinner.domain.demand.repository;

import com.backtracking.MrDinner.domain.user.repository.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Long>{
    List<Demand> findAllByUserId(User id);
}
