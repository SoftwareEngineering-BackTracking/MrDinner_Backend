package com.backtracking.MrDinner.domain.order.repository;

import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
@Entity
@NoArgsConstructor
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;

    @Column
    private String userId;

    @Column
    private Long basketNo;

    @Column
    private Dinner dinner;

    @Column
    private Style style;

}
