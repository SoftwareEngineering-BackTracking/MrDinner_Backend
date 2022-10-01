package com.backtracking.MrDinner.domain.user.controller;

import com.backtracking.MrDinner.domain.user.dto.UserCreateRequestDto;
import com.backtracking.MrDinner.domain.user.dto.UserCreateResponseDto;
import com.backtracking.MrDinner.domain.user.service.UserSerivce;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@Controller
public class UserController {

    private final UserSerivce userSerivce;

    // 계정 생성
    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUser(@RequestBody UserCreateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            userSerivce.createUser(requestDto);
            dtoMetaData = new DtoMetaData("계정 생성 성공");
            return ResponseEntity.ok(new UserCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserCreateResponseDto(dtoMetaData));
        }
    }
}
