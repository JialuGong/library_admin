package com.man.mandarin.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.man.mandarin.entity.Admin;
import com.man.mandarin.entity.User;

import java.util.Date;
public class TokenUtil {
    private static final long EXPIRE_TIME= 30*60*1000;
    private static final String TOKEN_SECRET="token123";  //密钥
    /**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(User user){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("authorization")
                    .withClaim("admin_name",user.getName())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }
    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            User admin=new User();
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("authorization").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("Certification passed!");
            System.out.println("issuer: " + jwt.getIssuer());
            System.out.println("user_name: " + jwt.getClaim("user_name").asString());
            System.out.println("expire date: " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
