package com.backtracking.MrDinner.domain.coupon.repository;

import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.*;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponNo;

    @Column
    private String userId;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private Long price;

    @Column
    private String name;

    @Builder
    public Coupon(String userId, Date startTime, Date endTime, Long price, String name){
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.name = name;
    }
}
