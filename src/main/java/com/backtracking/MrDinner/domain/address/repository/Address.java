package com.backtracking.MrDinner.domain.address.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressNo;

    @Column
    private String userId;

    @Column
    private String detail;


    @Builder
    public Address(String userId, String detail){
        this.userId = userId;
        this.detail = detail;
    }

    public void update(String detail){
        this.detail = detail;
    }
}
