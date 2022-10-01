package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.Data;

import javax.persistence.Column;

@Data
public class UserCreateRequestDto {

    private String id;
    private String password;
    private String name;
    private String phoneNumber;
    private String email;
    private String nickname;
    private int department;

    public User toEntity(){
        return User.builder()
                .id(id)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .nickname(nickname)
                .department(department)
                .build();
    }
}
