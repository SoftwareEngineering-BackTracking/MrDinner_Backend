package com.backtracking.MrDinner.global.security;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthCodeService {

    private final List<AuthCodeToken> verificatioTokenList = new LinkedList<>();

    public String generateAuthCode(String source){

        // 인증 코드 생성
        char[] authCodeCharSet =new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String authCode = TempCodeGenerator.generate(authCodeCharSet, 6);

        // 기존 인증코드가 존재한다면 제거
        verificatioTokenList.removeIf((token) -> (token.getSource().equals(source)));

        Calendar expiredTime = Calendar.getInstance();
        expiredTime.setTime(new Date());
        expiredTime.add(Calendar.MINUTE, 10);

        // 인증 코드 추가
        verificatioTokenList.add(new AuthCodeToken(source, authCode, expiredTime));

        return authCode;
    }

    public void validateAuthCode(String source, String authCode) {
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());

        for(AuthCodeToken token : verificatioTokenList){
            if(
                    token.getSource().equals(source) &&
                            token.getAuthCode().equals(authCode) &&
                                token.getExpiredTime().compareTo(currentTime) > 0
            ){
                verificatioTokenList.remove(token);
                return;
            }
        }

        throw new IllegalArgumentException("인증 코드가 맞지 않습니다.");
    }

    public void removeExpiredAuthCode(){
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());

        verificatioTokenList.removeIf((token) -> (token.getExpiredTime().compareTo(currentTime) <= 0));
    }
}
