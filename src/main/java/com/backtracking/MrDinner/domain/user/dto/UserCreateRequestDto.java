package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.Data;

import javax.persistence.Column;

@Data
public class UserCreateRequestDto {

    private String id;
    private String password;
    private String name;
    private String birth;
    private String phoneNumber;
    private String email;
    private String nickname;
    // 0 -> 고객
    // 1 -> 직원
    // 2 -> 비회원
    private Department department;

    public User toEntity(){
        return User.builder()
                .id(id)
                .password(password)
                .name(name)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .email(email)
                .nickname(nickname)
                .department(department)
                .build();
    }
}
