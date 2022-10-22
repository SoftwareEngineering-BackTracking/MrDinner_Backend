package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginRequestDto {
    private String id;
    private String password;
}
