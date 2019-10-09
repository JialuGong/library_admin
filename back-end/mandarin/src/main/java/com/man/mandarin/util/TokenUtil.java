package com.man.mandarin.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.man.mandarin.entity.Admin;

import java.util.Date;
public class TokenUtil {
    private static final long EXPIRE_TIME= 15*60*1000;
    private static final String TOKEN_SECRET="token123";  //密钥
    /**
     * 签名生成
     * @param admin
     * @return
     */
    public static String sign(Admin admin){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer(admin.getName())
                    .withClaim("admin_name", admin.getName())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
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
            Admin admin=new Admin();
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(admin.getName()).build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("Certification passed!");
            System.out.println("issuer: " + jwt.getIssuer());
            System.out.println("admin_name: " + jwt.getClaim("username").asString());
            System.out.println("expire date: " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
