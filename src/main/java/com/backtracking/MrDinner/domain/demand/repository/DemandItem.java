package com.backtracking.MrDinner.domain.demand.repository;

import com.backtracking.MrDinner.global.entitiy.BaseEntity;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class DemandItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.INSERT)
    private Long demandItemNo;

    @Column
    private Long demandNo;

    @Column
    private Dinner dinner;

    @Column
    private Style style;

    @Column
    private Long price;
    public void update(Dinner dinner, Style style, Long price) {
        this.dinner = dinner;
        this.style = style;
        this.price = price;
    }
}
