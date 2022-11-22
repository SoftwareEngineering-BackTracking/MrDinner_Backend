package com.backtracking.MrDinner.domain.demand.repository;

import com.backtracking.MrDinner.domain.address.repository.Address;
import com.backtracking.MrDinner.domain.coupon.repository.Coupon;
import com.backtracking.MrDinner.domain.purchase.repository.Purchase;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.global.entitiy.BaseEntity;
import com.backtracking.MrDinner.global.enumpackage.DemandStatus;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Demand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Generated(GenerationTime.INSERT)
    private Long demandno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User userId;

    @Column
    private Long price;

    @Column
    private DemandStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Coupon coupon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Purchase purchase;

    @Builder
    public Demand(User userId, Long price, DemandStatus status, Address address, Coupon coupon, Purchase purchase){
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.address = address;
        this.coupon = coupon;
        this.purchase = purchase;
    }

    public void update(Long price, DemandStatus status, Address address, Coupon coupon, Purchase purchase){
        this.price = price;
        this.status = status;
        this.address = address;
        this.coupon = coupon;
        this.purchase = purchase;
    }
}