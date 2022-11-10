package com.backtracking.MrDinner.domain.dinner.repository;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerIngredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class DinnerIngredientList {
    @Id
    @Column
    private DinnerIngredient dinnerIngredient;

    @Column
    private Dinner dinner;

    @Column
    private Long price;

    @Column
    private Long quantity;

    @Column
    private Date demandDate;

    @Builder
    public DinnerIngredientList(DinnerIngredient dinnerIngredient, Dinner dinner, Long price, Long quantity, Date demandDate){
        this.dinnerIngredient = dinnerIngredient;
        this.dinner = dinner;
        this.price = price;
        this.quantity = quantity;
        this.demandDate = demandDate;
    }

    public void update(Long quantity, Date demandDate){
        this.quantity = quantity;
        this.demandDate = demandDate;
    }
}
