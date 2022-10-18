package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class UserFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private String name;
    private String phoneNumber;
    private String email;
    private String nickname;
    private Department department;
    private List<User> userList;

    public UserFetchResponseDto(DtoMetaData dtoMetaData, User user){
        this.dtoMetaData = dtoMetaData;
        this.name = user.getName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.department = user.getDepartment();
    }
    public UserFetchResponseDto(DtoMetaData dtoMetaData, List<User> userList){
        this.dtoMetaData = dtoMetaData;
        this.userList = userList;
    }
    public UserFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
        this.name = null;
        this.phoneNumber = null;
        this.email = null;
        this.nickname = null;
        this.department = null;
    }

}
