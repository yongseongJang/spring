package com.yongseong.spring.util.jwt;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.yongseong.spring.dto.UserDetailsDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public String generateJwtToken(Authentication authentication) {
        UserDetailsDto userPrincipal = (UserDetailsDto) authentication.getPrincipal();

        JwtBuilder builder = Jwts.builder().setSubject(userPrincipal.getUsername()).setHeader(createHeader())
                .setExpiration(createExpireDate()).signWith(SignatureAlgorithm.HS256, createSigningKey());

        return builder.compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", new Date());

        return header;
    }

    private Date createExpireDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, JwtProperties.EXPIRATION_DATE);
        return c.getTime();
    }

    private Key createSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JwtProperties.SECRET);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String getUserEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: ", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: ", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: ", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: ", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: ", e.getMessage());
        }

        return false;
    }

}