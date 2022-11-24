package com.backtracking.MrDinner.domain.user.service;

import com.backtracking.MrDinner.domain.user.dto.*;
import com.backtracking.MrDinner.domain.user.repository.User;
import com.backtracking.MrDinner.domain.user.repository.UserRepository;
import com.backtracking.MrDinner.global.mail.MailService;
import com.backtracking.MrDinner.global.security.AuthCodeService;
import com.backtracking.MrDinner.global.voice.VoiceToken;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthCodeService authCodeService;
    private final MailService mailService;
    private final UserRepository userRepository;
    private final VoiceToken voiceToken;
    public void sendAuthCode(AuthCodeSendRequestDto requestDto) throws Exception {
        mailService.sendAuthCodeMail(requestDto.getEmail());
    }

    public void verifyAuthCode(AuthCodeVerifyRequestDto requestDto) {
        authCodeService.validateAuthCode(requestDto.getEmail(), requestDto.getAuthCode());
    }

    @Transactional
    public void login(LoginRequestDto requestDto, HttpSession session) throws IOException, ParseException {
//        if(session.getAttribute("id") != null){
//            session.removeAttribute("id");
//        }
//        System.out.println(requestDto.getId());
        User user = userRepository.findById(requestDto.getId()).orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

        if(!user.getPassword().equals(requestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀립니다.");
        }
        // 세션에 id 정보 저장
//        session.setAttribute("id", requestDto.getId());

        // 음성인식 토큰 생성
        String token = voiceToken.generateToken();
        session.setAttribute("token", token);
    }

    @Transactional
    public void checkId(CheckIdRequestDto requestDto) {
        if(userRepository.existsById(requestDto.getId())){
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }
    }


    @Transactional
    public void logout() {
//        String id = (String) session.getAttribute("id");
//        if(id != null){
//            // 세션에 아이디 정보 삭제
//            session.invalidate();
//        }

    }
}
