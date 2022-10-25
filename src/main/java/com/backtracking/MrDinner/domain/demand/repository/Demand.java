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
    private Long demandNo;

    @Column
    private String userId;

    @Column
    private Long price;

    @Column
    private String status;

    @Builder
    public Demand(String userId, Long price, String status){
        this.userId = userId;
        this.price = price;
        this.status = status;
    }
}
