package com.coinbot.infra.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtTokenProvider {
    @Value("${security.access-key}")
    private String accessKey;
    @Value("${security.secret-key}")
    private String secretKey;

    public String createToken() {
        final Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm);
    }
}
