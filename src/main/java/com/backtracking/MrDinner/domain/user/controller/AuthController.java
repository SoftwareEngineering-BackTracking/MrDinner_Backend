package com.backtracking.MrDinner.domain.user.controller;

import com.backtracking.MrDinner.domain.user.dto.*;
import com.backtracking.MrDinner.domain.user.service.AuthService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import com.backtracking.MrDinner.global.mail.MailService;
import com.backtracking.MrDinner.global.security.AuthCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/code/send")
    public ResponseEntity<AuthCodeSendResponseDto>sendAuthCode(@RequestBody AuthCodeSendRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            authService.sendAuthCode(requestDto);
            dtoMetaData = new DtoMetaData("메일 전송 완료");
            return ResponseEntity.ok(new AuthCodeSendResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthCodeSendResponseDto(dtoMetaData));
        }

    }

    @GetMapping("/code/verify")
    public ResponseEntity<AuthCodeVerifyResponseDto> verifyAuthCode(@RequestBody AuthCodeVerifyRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            authService.verifyAuthCode(requestDto);
            dtoMetaData = new DtoMetaData("인증 코드 검증 성공");
            return ResponseEntity.ok(new AuthCodeVerifyResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthCodeVerifyResponseDto(dtoMetaData));
        }
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            authService.login(requestDto);
            dtoMetaData = new DtoMetaData("로그인 성공");
            return ResponseEntity.ok(new LoginResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponseDto(dtoMetaData));
        }
    }

    @GetMapping("/signup/checkid")
    public ResponseEntity<CheckIdResponseDto> checkId(@RequestBody CheckIdRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            authService.checkId(requestDto);
            dtoMetaData = new DtoMetaData("사용 가능한 아이디입니다.");
            return ResponseEntity.ok(new CheckIdResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CheckIdResponseDto(dtoMetaData));
        }
    }
}
