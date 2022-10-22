package com.backtracking.MrDinner.domain.demand.repository;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;

    @Column
    private String userId;

    @Column
    private Long basketNo;

    @Enumerated(EnumType.STRING)
    private Dinner dinner;

    @Enumerated(EnumType.STRING)
    private Style style;

    @Builder
    public Demand(String userId, Long basketNo, Dinner dinner, Style style){
        this.userId = userId;
        this.basketNo = basketNo;
        this.dinner = dinner;
        this.style = style;
    }
}
