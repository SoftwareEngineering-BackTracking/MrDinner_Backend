package com.backtracking.MrDinner.domain.address.repository;

import com.backtracking.MrDinner.domain.cart.repository.Cart;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Address address;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(unique = true)
    private Cart cartNo;

    @Column
    private String detail;

    @Builder
    public CartAddress(Address address, Cart cartNo, String detail){
        this.address = address;
        this.cartNo = cartNo;
        this.detail = detail;
    }
}
