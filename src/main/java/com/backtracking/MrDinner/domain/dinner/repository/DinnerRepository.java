package com.backtracking.MrDinner.domain.dinner.repository;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinnerRepository extends JpaRepository<DinnerList, Dinner> {
}
