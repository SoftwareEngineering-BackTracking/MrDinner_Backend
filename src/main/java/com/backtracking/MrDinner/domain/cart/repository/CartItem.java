package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.global.entitiy.BaseEntity;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemNo;

    @Column
    private Long cartNo;

    @Enumerated(EnumType.STRING)
    private Dinner dinner;

    @Enumerated(EnumType.STRING)
    private Style style;

    @Column
    private Long price;

    @Builder
    public CartItem(Long cartNo, Dinner dinner, Style style, Long price) {
        this.cartNo = cartNo;
        this.dinner = dinner;
        this.style = style;
        this.price = price;
    }

    public void update(Dinner dinner, Style style){
        this.dinner = dinner;
        this.style = style;
    }
}