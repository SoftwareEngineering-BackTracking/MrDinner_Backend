package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartNo;

    @Column
    private String userId;

    @Builder
    public Cart(String userId){
        this.userId = userId;
    }
}
