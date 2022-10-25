package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.Data;

@Data
public class UserUpdateRequestDto {

    private String name;
    private String phoneNumber;
    private String email;
    private String nickname;
    private String address;
}
