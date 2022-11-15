package com.backtracking.MrDinner.domain.purchase.repository;

import com.backtracking.MrDinner.domain.user.repository.User;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User userId;

    @Column
    private String cardNumber;

    @Column
    private String bank;

    @Builder
    public Purchase(User userId, String cardNumber, String bank){
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.bank = bank;
    }
}
