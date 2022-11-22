package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
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

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn
    private Cart cartNo;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private Long price;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Coupon coupon;
}
