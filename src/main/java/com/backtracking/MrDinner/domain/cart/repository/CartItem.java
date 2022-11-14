package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.domain.dinner.repository.DinnerList;
import com.backtracking.MrDinner.domain.style.repository.StyleList;
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private Cart cartNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    @Enumerated(EnumType.STRING)
    private DinnerList dinner;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    @Enumerated(EnumType.STRING)
    private StyleList style;

    @Column
    private Long price;

    @Builder
    public CartItem(Cart cartNo, DinnerList dinner, StyleList style, Long price) {
        this.cartNo = cartNo;
        this.dinner = dinner;
        this.style = style;
        this.price = price;
    }

    public void update(DinnerList dinner, StyleList style){
        this.dinner = dinner;
        this.style = style;
    }
}