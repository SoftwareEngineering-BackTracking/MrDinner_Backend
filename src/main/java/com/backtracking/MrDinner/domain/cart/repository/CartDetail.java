package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.global.entitiy.BaseEntity;
import com.backtracking.MrDinner.global.enumpackage.DetailStatus;
import com.backtracking.MrDinner.global.enumpackage.Dinner;
import com.backtracking.MrDinner.global.enumpackage.DinnerStyle;
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
public class CartDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartDetailNo;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn
    private CartItem cartItemNo;

    @Column
    private String name;

    // 추가, 삭제
    @Enumerated(EnumType.STRING)
    private DetailStatus status;

    @Column
    private Long price;

    @Column
    private DinnerStyle dinnerStyle;

    @Builder
    public CartDetail(CartItem cartItemNo, String name, DetailStatus status, Long price, DinnerStyle dinnerStyle) {
        this.cartItemNo = cartItemNo;
        this.name = name;
        this.status = status;
        this.price = price;
        this.dinnerStyle = dinnerStyle;
    }

    public void update(String name, DetailStatus status){
        this.name = name;
        this.status = status;
    }
}
