package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.User;
import br.com.vvv.Helpers.DataHelper;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.security.Key;
import java.util.Date;

public abstract class AuthToken {
    protected final String content;

    public AuthToken(User user) {
        content = generateTokenContent(user);
    }

    public AuthToken(String _content) {
        content = _content;
    }

    public String getContent() {
        return content;
    }

    protected String getSecret() {
        return "0x3e5c7a6d180a6c802c88542e0dbf2277016b657c8e9909f9cb994ea1c06c2b37";
    }

    public Date getExpirationDate() {
        final int expirationDateInMs = 7200000; // 2 horas de validade
        long nowMillis = System.currentTimeMillis();
        long expirationMillis = nowMillis + expirationDateInMs;
        return new Date(expirationMillis);
    }

    @Contract("_ -> new")
    public static @NotNull AuthToken fromRequest(@NotNull HttpServletRequest request) throws RuntimeException {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return new AuthToken(authorizationHeader.replace("Bearer ", "")) {
                @Override
                protected String generateTokenContent(User user) {
                    return null;
                }
            };
        }

        throw new RuntimeException("[AuthToken.fromRequest] Auth Header faltando!");
    }

    protected abstract String generateTokenContent(User user) throws JwtException;

    public String getUserLogin() {
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
