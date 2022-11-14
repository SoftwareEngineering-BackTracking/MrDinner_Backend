package com.backtracking.MrDinner.domain.cart.repository;

import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.global.entitiy.BaseEntity;
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
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private User userId;

    @Builder
    public Cart(User userId){
        this.userId = userId;
    }
}
