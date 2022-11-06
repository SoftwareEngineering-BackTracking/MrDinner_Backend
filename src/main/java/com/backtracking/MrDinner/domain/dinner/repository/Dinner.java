package com.backtracking.MrDinner.domain.dinner.repository;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Dinner {

    @Id
    @Column
    private Dinner dinner;


}
