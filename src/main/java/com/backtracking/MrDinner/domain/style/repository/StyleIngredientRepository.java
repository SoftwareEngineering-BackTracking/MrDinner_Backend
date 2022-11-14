package com.backtracking.MrDinner.domain.style.repository;

import com.backtracking.MrDinner.global.enumpackage.Style;
import com.backtracking.MrDinner.global.enumpackage.StyleIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleIngredientRepository extends JpaRepository<StyleIngredientList, StyleIngredient> {
    List<StyleIngredientList> findAllByStyle(StyleList style);
}
