package com.backtracking.MrDinner.domain.address.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CartAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CartAddressNo;

    @Column
    private Long cartNo;

    @Column
    private String detail;

    @Builder
    public CartAddress(Long cartNo, String detail){
        this.cartNo = cartNo;
        this.detail = detail;
    }
}
