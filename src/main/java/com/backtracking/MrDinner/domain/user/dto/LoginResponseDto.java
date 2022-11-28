package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginResponseDto {
    private DtoMetaData dtoMetaData;
    private String token;

    public LoginResponseDto(DtoMetaData dtoMetaData, String token){
        this.dtoMetaData = dtoMetaData;
        this.token = token;
    }

    public LoginResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
