package com.backtracking.MrDinner.domain.user.service;

import com.backtracking.MrDinner.domain.user.dto.UserCreateRequestDto;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserSerivce {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserCreateRequestDto requestDto) {
        String id = requestDto.getEmail();
        if(userRepository.existsById(id)){
            throw new IllegalArgumentException("이미 가입되어 있는 유저입니다. id: " + id);
        }
        userRepository.save(requestDto.toEntity());
    }
}
