package com.backtracking.MrDinner.domain.user.dto;

import lombok.Data;

@Data
public class PasswordRecoveryRequestDto {

    // 본인 인증 후 아이디를 입력하면 알맞은 비밀번호 수정 가능
    private String id;
    private String newPassword;
}
