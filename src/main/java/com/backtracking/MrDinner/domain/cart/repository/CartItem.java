package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemNo;

    @Column
    private Long cartNo;

    @Enumerated(EnumType.STRING)
    private Dinner dinner;

    @Enumerated(EnumType.STRING)
    private Style style;

    @Builder
    public CartItem(Long cartNo, Dinner dinner, Style style) {
        this.cartNo = cartNo;
        this.dinner = dinner;
        this.style = style;
    }

    public void update(Dinner dinner, Style style){
        this.dinner = dinner;
        this.style = style;
    }
}