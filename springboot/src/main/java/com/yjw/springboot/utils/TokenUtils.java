package com.yjw.springboot.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

public class TokenUtils {

    /**
     * 生成 token
     * @param userId
     * @param sign
     * @return
     */
    public static String getToken(String userId,String sign){
        return JWT.create().withAudience(userId) //将userid保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),1))//1小时token有效
                .sign(Algorithm.HMAC256(sign)); //以password作为token密钥
    }

}
