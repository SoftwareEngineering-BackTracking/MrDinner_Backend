package com.backtracking.MrDinner.domain.user.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String email;
    // private String image;

    @Column
    private String nickname;

    @Column
    private int department;

    @Builder
    public User(String id, String password, String name, String phoneNumber, String email, String nickname, int department){
        this.id = id;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nickname = nickname;
        this.department =department;
    }

    public void update(String name, String phoneNumber, String email, String nickname){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.nickname = nickname;
    }
}
