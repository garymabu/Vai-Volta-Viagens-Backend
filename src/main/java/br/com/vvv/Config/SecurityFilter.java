package br.com.vvv.Config;

import br.com.vvv.Domain.DTO.AuthToken;
import br.com.vvv.Domain.Enum.Role;
import br.com.vvv.Service.ClientService;
import br.com.vvv.Service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.time.ZoneId;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final String[] unfilteredRoutes = {
            "/v1/auth/client",
            "/v1/auth/employee",
            "/v1/client",
            "/v1/employee"
    };
    @Autowired
    private ClientService clientService;
    @Autowired
    private EmployeeService employeeService;

    private HttpServletResponse tokenValidation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            AuthToken token = AuthToken.fromRequest(request);
            UsernamePasswordAuthenticationToken authentication = null;

            LocalDate expirationLocalDate = token.getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


            if(LocalDate.now().isAfter(expirationLocalDate) || LocalDate.now().isEqual(expirationLocalDate))
                throw new RuntimeException("[AuthToken.fromRequest] Auth Header expirado!");
            var role = token.getRole();
            if(role.equals(Role.CLIENT)) {
                var login = token.getUserLogin();
                var client = clientService.findByLogin(login);
                authentication = new UsernamePasswordAuthenticationToken(
                        client,
                        null,
                        client.getAuthorities()
                );
            } else if(role.equals(Role.EMPLOYEE)) {
                var login = token.getUserLogin();
                var employee = employeeService.findByLogin(login);
                authentication = new UsernamePasswordAuthenticationToken(
                        employee,
                        null,
                        employee.getAuthorities()
                );
            }

            if(authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            else throw new RuntimeException("[AuthToken.fromRequest] Autenticação falhou!");

        } catch (RuntimeException exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String errorMessage = exception.getMessage();
            response.getWriter().write("{\"message\": \"" + errorMessage + "\"}");
            return response;
        }

        return response;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String path = request.getRequestURI();
        if(!Arrays.stream(unfilteredRoutes).anyMatch(s -> s.equals(path))) {
            response = tokenValidation(request,response);
        }
        filterChain.doFilter(request, response);
    }
}
