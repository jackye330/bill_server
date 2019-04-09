package com.Util;

import java.security.SecureRandom;
import java.util.Random;

public class VCUtil {
    public static String vc;
    //验证码范围
    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final Random random = new SecureRandom();

    //获取长度为n的验证码
    public static String getVerificationCode(int n){
        char[] veriChar = new char[n];

        for (int i = 0; i < n; ++i){
            veriChar[i] = SYMBOLS.charAt(random.nextInt(SYMBOLS.length()));
        }
        String res = new String(veriChar);
        vc = res;
        return res;
    }
}
