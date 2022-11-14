package com.backtracking.MrDinner.domain.demand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandDetailRepository extends JpaRepository<DemandDetail, Long> {
    List<DemandDetail> findAllByDemandItemNo(DemandItem demandItemNo);
}
