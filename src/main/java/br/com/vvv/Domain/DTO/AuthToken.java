package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Helpers.DataHelper;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

public class AuthToken {
    private final String content;
    public AuthToken(Client client) {
        content = generateTokenContent(client);
    }
    public AuthToken(String _content) {
        content = _content;
    }

    public String getContent() {
        return content;
    }
    private String getSecret() {
        return System.getenv("token.secret");
    }
    public Date getExpirationDate() {
        final int expirationDateInMs = 7200000;
        long nowMillis = System.currentTimeMillis();
        long expirationMillis = nowMillis + expirationDateInMs; // 2 horas de validade
        return new Date(expirationMillis);
    }
    @Contract("_ -> new")
    public static @NotNull AuthToken fromRequest(@NotNull HttpServletRequest request)
            throws RuntimeException {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return new AuthToken(
                authorizationHeader.replace("Bearer ", "")
            );
        }

        throw new RuntimeException ("[AuthToken.fromRequest] Auth Header faltando!");
    }

    private String generateTokenContent(@org.jetbrains.annotations.NotNull Client client)
        throws JwtException {
        Key key = DataHelper.parseStringToKey(getSecret());

        return Jwts.builder()
                .setClaims(Jwts.claims().setSubject(client.getLogin()))
                .setIssuedAt(this.getExpirationDate())
                .signWith(key)
                .compact();
    }

    public String getClientLogin() {
        try {
            Key key = DataHelper.parseStringToKey(getSecret());
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(content)
                    .getBody()
                    .getSubject();
        } catch (JwtException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
}
