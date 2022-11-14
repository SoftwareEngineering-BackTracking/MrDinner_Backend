package com.backtracking.MrDinner.domain.style.repository;

import com.backtracking.MrDinner.global.enumpackage.Style;
import com.backtracking.MrDinner.global.enumpackage.StyleIngredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class StyleIngredientList {

    @Id
    @Column
    private StyleIngredient styleIngredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private StyleList style;

    @Column
    private Long price;

    @Column
    private Long quantity;

    @Column
    private Date demandDate;

    @Builder
    public StyleIngredientList(StyleIngredient styleIngredient, StyleList style, Long price, Long quantity, Date demandDate){
        this.styleIngredient = styleIngredient;
        this.style = style;
        this.price = price;
        this.quantity = quantity;
        this.demandDate = demandDate;
    }

    public void update(Long quantity, Date demandDate){
        this.quantity = quantity;
        this.demandDate = demandDate;
    }

}
