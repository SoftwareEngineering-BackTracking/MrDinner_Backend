package com.backtracking.MrDinner.domain.dinner.repository;

import com.backtracking.MrDinner.global.enumpackage.DinnerIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinnerIngredientRepository extends JpaRepository<DinnerIngredientList, DinnerIngredient> {
}
