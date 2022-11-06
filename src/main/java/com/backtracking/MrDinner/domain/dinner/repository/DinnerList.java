package com.backtracking.MrDinner.domain.dinner.repository;

import com.backtracking.MrDinner.global.enumpackage.Dinner;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class DinnerList {

    @Id
    @Column
    private Dinner dinner;

    @Column
    private Long price;

    @Column
    private String detail;
}
