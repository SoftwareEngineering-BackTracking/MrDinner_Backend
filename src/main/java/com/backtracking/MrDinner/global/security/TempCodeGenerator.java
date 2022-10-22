package com.backtracking.MrDinner.global.security;

import java.security.SecureRandom;
import java.util.Date;

public class TempCodeGenerator {

    public static String generate(char[] charSet, int length){
        StringBuilder authCodeBuffer = new StringBuilder();
        SecureRandom secureRandomNumberGenerator = new SecureRandom();
        secureRandomNumberGenerator.setSeed(new Date().getTime());

        int randomCharIndex;
        for(int i = 0 ; i < length ; i++){
            randomCharIndex = secureRandomNumberGenerator.nextInt(charSet.length);
            authCodeBuffer.append(charSet[randomCharIndex]);
        }
        return authCodeBuffer.toString();
    }
}
