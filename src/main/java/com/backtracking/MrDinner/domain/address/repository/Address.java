package com.backtracking.MrDinner.domain.address.repository;

import com.backtracking.MrDinner.domain.user.repository.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressNo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn
    private User userId;

    @Column
    private String detail;


    @Builder
    public Address(User userId, String detail){
        this.userId = userId;
        this.detail = detail;
    }

    public void update(String detail){
        this.detail = detail;
    }
}
