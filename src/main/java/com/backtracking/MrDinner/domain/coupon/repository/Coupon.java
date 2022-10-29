package com.backtracking.MrDinner.domain.coupon.repository;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
}
