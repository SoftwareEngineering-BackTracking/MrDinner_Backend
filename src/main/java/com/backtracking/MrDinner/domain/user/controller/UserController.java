package com.backtracking.MrDinner.domain.user.controller;

import com.backtracking.MrDinner.domain.user.dto.*;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.service.UserSerivce;
import com.backtracking.MrDinner.global.dto.DtoMetaData;
import com.backtracking.MrDinner.global.enumpackage.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/user")
@Controller
public class UserController {
    private Department department;
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

    // 계정 조회
    @GetMapping
    public ResponseEntity<UserFetchResponseDto> fetchUser(@RequestBody UserFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            if(requestDto.getId() != null){
                User user = userSerivce.fetchUser(requestDto);
                dtoMetaData = new DtoMetaData("계정 조회 성공");
                return ResponseEntity.ok(new UserFetchResponseDto(dtoMetaData, user));
            }
            else if(requestDto.getName() != null || requestDto.getDepartment() == department.고객 || requestDto.getDepartment() == department.직원 || requestDto.getDepartment() == department.비회원){
                List<User> userList = userSerivce.fetchFilterUser(requestDto);
                dtoMetaData = new DtoMetaData("계정 필터별 조회 성공");
                return ResponseEntity.ok(new UserFetchResponseDto(dtoMetaData, userList));
            }
            else{
                List<User> userList = userSerivce.fetchAllUser(requestDto);
                dtoMetaData = new DtoMetaData("모든 계정 조회 성공");
                return ResponseEntity.ok(new UserFetchResponseDto(dtoMetaData, userList));
            }
        }
        catch(Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserFetchResponseDto(dtoMetaData));
        }
    }

    // 계정 수정
    @PutMapping
    public ResponseEntity<UserUpdateResponseDto> updateUser(@RequestBody UserUpdateRequestDto requestDto, HttpSession session){
        DtoMetaData dtoMetaData;

        try{
            userSerivce.updateUser(requestDto, session);
            dtoMetaData = new DtoMetaData("계정 수정 성공");
            return ResponseEntity.ok(new UserUpdateResponseDto(dtoMetaData));

        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserUpdateResponseDto(dtoMetaData));
        }
    }

    // 계정 삭제
    @DeleteMapping
    public ResponseEntity<UserDeleteResponseDto> deleteUser(@RequestBody UserDeleteRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            userSerivce.deleteUser(requestDto);
            dtoMetaData = new DtoMetaData("계정 삭제 완료");
            return ResponseEntity.ok(new UserDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserDeleteResponseDto(dtoMetaData));
        }
    }
}
