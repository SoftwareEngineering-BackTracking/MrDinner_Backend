package com.backtracking.MrDinner.domain.demand.repository;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class DemandItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandItemNo;

    @Column
    private Long demandNo;

    @Column
    private Dinner dinner;

    @Column
    private Style style;
}
