package com.backtracking.MrDinner.domain.cart.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CartCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartCouponNo;

    @Column
    private Long cartNo;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private Long price;

    @Column
    private String name;
}
