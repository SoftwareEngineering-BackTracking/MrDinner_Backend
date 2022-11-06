package com.backtracking.MrDinner.domain.style.repository;

import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class StyleList {

    @Id
    @Column
    private Style style;

    @Column
    private Long price;

    @Column
    private String detail;
}
