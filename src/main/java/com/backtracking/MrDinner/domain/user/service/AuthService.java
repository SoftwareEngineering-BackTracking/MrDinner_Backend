package com.backtracking.MrDinner.domain.user.service;

import com.backtracking.MrDinner.domain.user.dto.*;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import com.backtracking.MrDinner.global.mail.MailService;
import com.backtracking.MrDinner.global.security.AuthCodeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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
    public void login(LoginRequestDto requestDto, HttpSession session) {
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

        if(!user.getPassword().equals(requestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        // 로그인 성공시 세션 저장
        session.setAttribute("id", requestDto.getId());
    }

    @Transactional
    public void checkId(CheckIdRequestDto requestDto) {
        if(userRepository.existsById(requestDto.getId())){
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }
    }


    @Transactional
    public void logout(LogoutRequestDto requestDto, HttpSession session) {
        String id = (String) session.getAttribute("id");
        if(id != null){
            session.invalidate();
        }
    }
}
