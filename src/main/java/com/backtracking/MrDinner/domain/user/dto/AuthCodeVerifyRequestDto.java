package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class AuthCodeVerifyRequestDto {
    private String email;
    private String authCode;
}
