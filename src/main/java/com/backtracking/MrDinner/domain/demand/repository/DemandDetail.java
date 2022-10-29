package com.backtracking.MrDinner.domain.demand.repository;

import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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
}
