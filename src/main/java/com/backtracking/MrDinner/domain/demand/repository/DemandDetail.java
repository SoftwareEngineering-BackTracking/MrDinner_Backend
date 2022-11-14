package com.backtracking.MrDinner.domain.demand.repository;

import com.backtracking.MrDinner.global.entitiy.BaseEntity;
import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerStyle;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DemandDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandDetailNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DemandItem demandItemNo;

    @Column
    private String name;

    @Column
    private DetailStatus status;

    @Column
    private Long price;

    @Column
    private DinnerStyle dinnerStyle;

    public void update(String name, DetailStatus status, Long price, DinnerStyle dinnerStyle) {
        this.name = name;
        this.status = status;
        this.price = price;
        this.dinnerStyle = dinnerStyle;
    }
}
