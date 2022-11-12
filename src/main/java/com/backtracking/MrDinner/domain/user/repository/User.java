package com.backtracking.MrDinner.domain.user.repository;

import com.backtracking.MrDinner.global.entitiy.BaseEntity;
import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

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

    @Enumerated(EnumType.STRING)
    private Department department;

//    @Column
//    private String address;

    @Builder
    public User(String id, String password, String name, String phoneNumber, String email, String nickname, Department department){
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

    public void passwordUpdate(String password){
        this.password = password;
    }
}
