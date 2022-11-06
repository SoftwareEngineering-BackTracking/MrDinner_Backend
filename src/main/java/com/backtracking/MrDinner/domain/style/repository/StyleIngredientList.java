package com.backtracking.MrDinner.domain.style.repository;

import com.backtracking.MrDinner.global.enumpackage.Style;
import com.backtracking.MrDinner.global.enumpackage.StyleIngredient;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class StyleIngredientList {

    @Id
    @Column
    private StyleIngredient styleIngredient;

    @Column
    private Style style;

    @Column
    private Long price;
}
