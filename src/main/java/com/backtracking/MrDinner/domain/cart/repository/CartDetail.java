package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.Style;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartDetailNo;

    @Column
    private Long cartItemNo;

    @Column
    private String name;

    // 추가, 삭제
    @Enumerated(EnumType.STRING)
    private DetailStatus status;

    @Builder
    public CartDetail(Long cartItemNo, String name, DetailStatus status) {
        this.cartItemNo = cartItemNo;
        this.name = name;
        this.status = status;
    }

    public void update(String name, DetailStatus status){
        this.name = name;
        this.status = status;
    }
}
