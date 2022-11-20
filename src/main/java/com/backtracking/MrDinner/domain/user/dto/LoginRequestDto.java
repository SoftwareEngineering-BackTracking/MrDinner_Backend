package com.backtracking.MrDinner.domain.user.dto;

import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@AllArgsConstructor
public class LoginRequestDto {
    private String id;
    private String password;
}
