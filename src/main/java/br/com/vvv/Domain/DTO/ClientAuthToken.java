package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Domain.Entity.User;
import br.com.vvv.Domain.Enum.Role;
import br.com.vvv.Helpers.DataHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.security.Key;

public class ClientAuthToken extends AuthToken {

    public ClientAuthToken(Client client) {
        super(client);
    }

    public ClientAuthToken(String _content) {
        super(_content);
    }

    @Override
    public String generateTokenContent(User user) throws JwtException {
        Client employee = (Client) user;
        Key key = DataHelper.parseStringToKey(getSecret());

        Claims claims = Jwts.claims().setSubject(employee.getLogin());
        claims.put("role", Role.CLIENT);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.getExpirationDate())
                .signWith(key)
                .compact();
    }
}
