package com.backtracking.MrDinner.global.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class AuthCodeToken {
    private String source;
    private String authCode;
    private Calendar expiredTime;
}
