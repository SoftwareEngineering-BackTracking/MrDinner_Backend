package com.backtracking.MrDinner.domain.user.controller;

import com.backtracking.MrDinner.domain.user.dto.*;
import com.backtracking.MrDinner.domain.user.service.AuthService;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import com.backtracking.MrDinner.global.mail.MailService;
import com.backtracking.MrDinner.global.security.AuthCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
    public ResponseEntity<AuthCodeVerifyResponseDto> verifyAuthCode(@RequestHeader Map<String, String> params){
        DtoMetaData dtoMetaData;
        AuthCodeVerifyRequestDto requestDto = new AuthCodeVerifyRequestDto(params.get("email"), params.get("authcode"));
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
    public ResponseEntity<LoginResponseDto> login(@RequestHeader Map<String, String> params, HttpSession session){
        DtoMetaData dtoMetaData;
        LoginRequestDto requestDto = new LoginRequestDto(params.get("id"), params.get("password"));
        try{
            authService.login(requestDto, session);
            dtoMetaData = new DtoMetaData("로그인 성공");
            return ResponseEntity.ok(new LoginResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponseDto(dtoMetaData));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logout(HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            authService.logout(session);
            dtoMetaData = new DtoMetaData("로그아웃 성공");
            return ResponseEntity.ok(new LogoutResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LogoutResponseDto(dtoMetaData));
        }
    }

    @GetMapping("/signup/checkid")
    public ResponseEntity<CheckIdResponseDto> checkId(@RequestHeader Map<String, String> params){
        DtoMetaData dtoMetaData;
        CheckIdRequestDto requestDto = new CheckIdRequestDto(params.get("id"));
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
