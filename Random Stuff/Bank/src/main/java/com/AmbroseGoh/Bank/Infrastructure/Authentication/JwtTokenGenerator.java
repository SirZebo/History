package com.AmbroseGoh.Bank.Infrastructure.Authentication;

import com.AmbroseGoh.Bank.Application.Common.Interfaces.Authentication.IJwtTokenGenerator;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

// Generate a Token in Java https://developer.okta.com/blog/2018/10/31/jwts-with-java
public class JwtTokenGenerator implements IJwtTokenGenerator {

    private static String SECRET_KEY = "this is my custom Secret key for authentication";
    private static final String ISSUER = "SIT_Bank";

    public String generateToken(UUID userId, String firstName, String lastName, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingCredentials = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // JWT Claims (Not too sure how to set subject, claims and id)
        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setSubject(userId.toString())
                .claim("firstName", firstName)
                .claim("lastName", lastName)
                .setIssuer(ISSUER)
                .signWith(signatureAlgorithm, signingCredentials);

        // expiration date
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
}
