package com.backtracking.MrDinner.domain.user.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class IdRecoveryRequestDto {

    // 본인 인증 후 이메일 쓰면 앎맞은 아이디 반환
    private String email;
}
