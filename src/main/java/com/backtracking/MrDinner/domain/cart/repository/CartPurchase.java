package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private Cart cartNo;

    @Column
    private String cardNumber;

    @Column
    private String bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Purchase purchase;
}
