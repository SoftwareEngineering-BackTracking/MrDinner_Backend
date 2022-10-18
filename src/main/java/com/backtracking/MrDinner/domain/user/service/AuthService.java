package com.backtracking.MrDinner.domain.user.service;

import com.backtracking.MrDinner.domain.user.dto.AuthCodeSendRequestDto;
import com.backtracking.MrDinner.domain.user.dto.AuthCodeVerifyRequestDto;
import com.backtracking.MrDinner.domain.user.dto.CheckIdRequestDto;
import com.backtracking.MrDinner.domain.user.dto.LoginRequestDto;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import com.backtracking.MrDinner.global.mail.MailService;
import com.backtracking.MrDinner.global.security.AuthCodeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthCodeService authCodeService;
    private final MailService mailService;
    private final UserRepository userRepository;

    public void sendAuthCode(AuthCodeSendRequestDto requestDto) throws Exception {
        mailService.sendAuthCodeMail(requestDto.getEmail());
    }

    public void verifyAuthCode(AuthCodeVerifyRequestDto requestDto) {
        authCodeService.validateAuthCode(requestDto.getEmail(), requestDto.getAuthCode());
    }

    @Transactional
    public void login(LoginRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

        if(!user.getPassword().equals(requestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
    }

    @Transactional
    public void checkId(CheckIdRequestDto requestDto) {
        if(userRepository.existsById(requestDto.getId())){
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }
    }


}
