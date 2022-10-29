package com.backtracking.MrDinner.domain.cart.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartPurchaseNo;

    @Column
    private String cardNumber;

    @Column
    private String bank;

}
