package com.backtracking.MrDinner.domain.user.service;

import com.backtracking.MrDinner.domain.user.dto.UserCreateRequestDto;
import com.backtracking.MrDinner.domain.user.dto.UserDeleteRequestDto;
import com.backtracking.MrDinner.domain.user.dto.UserFetchRequestDto;
import com.backtracking.MrDinner.domain.user.dto.UserUpdateRequestDto;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserSerivce {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public void createUser(UserCreateRequestDto requestDto) {
        String id = requestDto.getEmail();
        if(userRepository.existsById(id)){
            throw new IllegalArgumentException("이미 가입되어 있는 유저입니다. ID: " + id);
        }
        userRepository.save(requestDto.toEntity());
    }

    // 내 정보 보기
    @Transactional
    public User fetchUser(UserFetchRequestDto requestDto) {
        String id = requestDto.getId();
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 유저가 없습니다."));
    }

    // 내 정보 수정
    @Transactional
    public void updateUser(UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 유저가 없습니다."));

        user.update(requestDto.getName(), requestDto.getPhoneNumber(), requestDto.getEmail(), requestDto.getNickname());
    }

    // 회원 탈퇴
    @Transactional
    public void deleteUser(UserDeleteRequestDto requestDto){
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 유저가 없숩니다."));

        userRepository.delete(user);
    }
}
