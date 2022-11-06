package com.backtracking.MrDinner.domain.dinner.repository;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerIngredient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class DinnerIngredientList {

    @Id
    @Column
    private DinnerIngredient dinnerIngredient;

    @Column
    private Dinner dinner;

    @Column
    private Long price;
}
