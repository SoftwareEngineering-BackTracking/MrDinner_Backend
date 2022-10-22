package com.backtracking.MrDinner.domain.user.controller;

import com.backtracking.MrDinner.domain.user.dto.IdRecoveryRequestDto;
import com.backtracking.MrDinner.domain.user.dto.IdRecoveryResponseDto;
import com.backtracking.MrDinner.domain.user.dto.PasswordRecoveryRequestDto;
import com.backtracking.MrDinner.domain.user.dto.PasswordRecoveryResponseDto;
import com.backtracking.MrDinner.domain.user.service.UserRecoveryService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/recover")
@RequiredArgsConstructor
public class UserRecoveryController {

    private final UserRecoveryService userRecoveryService;

    @GetMapping("/id")
    public ResponseEntity<IdRecoveryResponseDto> recoverId(@RequestBody IdRecoveryRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            String id = userRecoveryService.recoverId(requestDto);
            dtoMetaData = new DtoMetaData("아이디 찾기 완료");
            return ResponseEntity.ok(new IdRecoveryResponseDto(dtoMetaData, id));
        }
        catch(Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new IdRecoveryResponseDto(dtoMetaData));
        }
    }

    @PutMapping("/password")
    public ResponseEntity<PasswordRecoveryResponseDto> recoverPassword(@RequestBody PasswordRecoveryRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            userRecoveryService.recoverPassword(requestDto);
            dtoMetaData = new DtoMetaData("비밀번호 수정 완료");
            return ResponseEntity.ok(new PasswordRecoveryResponseDto(dtoMetaData));
        }
        catch(Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PasswordRecoveryResponseDto(dtoMetaData));
        }
    }
}
