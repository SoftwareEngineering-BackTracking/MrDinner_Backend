package com.backtracking.MrDinner.domain.demand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandItemRepository extends JpaRepository<DemandItem, Long> {
    List<DemandItem> findAllByDemandNo(Demand demandno);
}
