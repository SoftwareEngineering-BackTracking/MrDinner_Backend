package com.backtracking.MrDinner.domain.dinner.repository;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DinnerIngredientRepository extends JpaRepository<DinnerIngredientList, DinnerIngredient> {
    List<DinnerIngredientList> findAllByDinner(DinnerList dinner);
}
