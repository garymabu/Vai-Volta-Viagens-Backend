package br.com.vvv.Service;

import java.security.Key;
import java.util.Date;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.entity.Client;
import br.com.vvv.Helpers.TokenHelpers;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenService {

    @Value("${token.secret}")
    String secret;

    public String generateToken(Client client) {
        String generateMethod = "[TokenService.generateToken]";
        log.info(generateMethod + " - [initializing the generate token method]");

        Key key = TokenHelpers.parseStringToKey(secret);

        try {
            return this.builderTokenJwts(client, key);            
        } catch (JwtException ex) {
            log.info(generateMethod + " - [Error generating token]: "+ ex.getMessage());
            return null;
        }
    }

    private Date getTokenExpirationDate() {
        long nowMillis = System.currentTimeMillis();
        long expirationMillis = nowMillis + 7200000; // 2 horas de validade
        return new Date(expirationMillis);
    }

    public String builderTokenJwts(Client client, Key key) {
        return Jwts.builder()
            .setClaims(Jwts.claims().setSubject(client.getLogin().toString()))
            .setIssuedAt(this.getTokenExpirationDate())
            .signWith(key)
            .compact();
    }

    public String getSubject(String tokenJWT) {
        try {
            Key key = TokenHelpers.parseStringToKey(secret);
            return Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(tokenJWT)
                        .getBody()
                        .getSubject();
        } catch (JwtException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
    
}