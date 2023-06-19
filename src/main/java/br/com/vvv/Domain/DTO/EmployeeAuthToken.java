package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Domain.Entity.User;
import br.com.vvv.Domain.Enum.Role;
import br.com.vvv.Helpers.DataHelper;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import java.security.Key;

public class EmployeeAuthToken extends AuthToken {

    public EmployeeAuthToken(Employee employee) {
        super(employee);
    }

    public EmployeeAuthToken(String _content) {
        super(_content);
    }

    @Override
    public String generateTokenContent(User user) throws JwtException {
        Employee employee = (Employee) user;
        Key key = DataHelper.parseStringToKey(getSecret());

        Claims claims = Jwts.claims().setSubject(employee.getLogin());
        claims.put("role", Role.EMPLOYEE);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.getExpirationDate())
                .signWith(key)
                .compact();
    }
}
