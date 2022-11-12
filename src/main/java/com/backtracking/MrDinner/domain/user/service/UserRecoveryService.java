package com.backtracking.MrDinner.domain.user.service;

import com.backtracking.MrDinner.domain.user.dto.IdRecoveryRequestDto;
import com.backtracking.MrDinner.domain.user.dto.PasswordRecoveryRequestDto;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserRecoveryService {
    private final UserRepository userRepository;

    @Transactional
    public String recoverId(IdRecoveryRequestDto requestDto) {
        // 유저의 id를 반환
        String email = requestDto.getEmail();

        if(userRepository.existsByEmail(email)){
            User user = userRepository.findByEmail(email);
            return user.getId();
        }
        else{
            throw new IllegalArgumentException("해당 이메일을 가진 계정이 없습니다.");
        }

    }

    @Transactional
    public void recoverPassword(PasswordRecoveryRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 계정이 없습니다."));

        user.passwordUpdate(requestDto.getNewPassword());

    }
}
