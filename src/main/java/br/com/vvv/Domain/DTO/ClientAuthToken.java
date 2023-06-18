package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Domain.Entity.User;
import br.com.vvv.Helpers.DataHelper;
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
    protected String generateTokenContent(User user) throws JwtException {
        Client client = (Client) user;
        Key key = DataHelper.parseStringToKey(getSecret());

        return Jwts.builder()
                .setClaims(Jwts.claims().setSubject(client.getLogin()))
                .setIssuedAt(this.getExpirationDate())
                .signWith(key)
                .compact();
    }
}
