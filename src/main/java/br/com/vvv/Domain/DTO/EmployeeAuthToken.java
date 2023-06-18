package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Domain.Entity.User;
import br.com.vvv.Helpers.DataHelper;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.security.Key;

public class EmployeeAuthToken extends AuthToken {

    public EmployeeAuthToken(Employee employee) {
        super(employee);
    }

    public EmployeeAuthToken(String _content) {
        super(_content);
    }

    @Override
    protected String generateTokenContent(User user) throws JwtException {
        Employee employee = (Employee) user;
        Key key = DataHelper.parseStringToKey(getSecret());

        return Jwts.builder()
                .setClaims(Jwts.claims().setSubject(employee.getLogin()))
                .setIssuedAt(this.getExpirationDate())
                .signWith(key)
                .compact();
    }
}
