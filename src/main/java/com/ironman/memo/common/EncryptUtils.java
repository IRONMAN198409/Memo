package com.ironman.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	// 암호화 기능
    // 암호화할 문자열을 전달받고, 암호화된 문자열을 리턴

    public static String md5(String message) {

        String resultString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            
            byte[] bytes = message.getBytes();
            md.update(bytes);

            byte[] digest = md.digest();

            // byte에 담긴 하나의 값을 16진수 문자로 만듦: 사용하기 편하도록
            for(int i = 0; i < digest.length; i++) {
                resultString += Integer.toHexString(digest[i] & 0xff);    // 2진수로 연산 
            }

        } catch( NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return resultString;

    }
    
}
