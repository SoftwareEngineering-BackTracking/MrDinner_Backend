package com.backtracking.MrDinner.domain.coupon.repository;

import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.*;
import org.springframework.data.util.Lazy;
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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private User userId;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private Long price;

    @Column
    private String name;

    @Builder
    public Coupon(User userId, Date startTime, Date endTime, Long price, String name){
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.name = name;
    }
}
