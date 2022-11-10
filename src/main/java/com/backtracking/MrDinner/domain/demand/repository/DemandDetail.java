package com.backtracking.MrDinner.domain.demand.repository;

import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DemandDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandDetailNo;

    @Column
    private Long demandItemNo;

    @Column
    private String name;

    @Column
    private DetailStatus status;

    @Column
    private Long price;

    public void update(String name, DetailStatus status, Long price) {
        this.name = name;
        this.status = status;
        this.price = price;
    }
}
