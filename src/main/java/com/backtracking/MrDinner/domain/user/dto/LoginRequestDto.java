package com.backtracking.MrDinner.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDto {
    private String id;
    private String password;
}
