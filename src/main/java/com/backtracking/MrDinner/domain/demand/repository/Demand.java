package com.backtracking.MrDinner.domain.demand.repository;

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

    @Column
    private String userId;

    @Column
    private Long price;

    @Column
    private DemandStatus status;

    @Column
    private Long address;

    @Column
    private Long coupon;

    @Column
    private Long purchase;

    @Builder
    public Demand(String userId, Long price, DemandStatus status, Long address, Long coupon, Long purchase){
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.address = address;
        this.coupon = coupon;
        this.purchase = purchase;
    }

    public void update(Long price, DemandStatus status, Long address, Long coupon, Long purchase){
        this.price = price;
        this.status = status;
        this.address = address;
        this.coupon = coupon;
        this.purchase = purchase;
    }
}