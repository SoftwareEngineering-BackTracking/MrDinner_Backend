package com.backtracking.MrDinner.domain.purchase.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseNo;

    @Column
    private String userId;

    @Column
    private String cardNumber;

    @Column
    private String bank;

    @Builder
    public Purchase(String userId, String cardNumber, String bank){
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.bank = bank;
    }
}
