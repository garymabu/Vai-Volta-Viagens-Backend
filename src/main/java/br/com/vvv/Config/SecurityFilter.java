package br.com.vvv.Config;

import br.com.vvv.Domain.DTO.AuthToken;
import br.com.vvv.Domain.DTO.ClientAuthToken;
import br.com.vvv.Domain.DTO.EmployeeAuthToken;
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
import java.util.Arrays;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final String[] unfilteredRoutes = {
            "/v1/auth",
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

            if(token instanceof ClientAuthToken) {
                var login = token.getUserLogin();
                var client = clientService.findByLogin(login);
                authentication = new UsernamePasswordAuthenticationToken(
                        client,
                        null,
                        client.getAuthorities()
                );
            } else if(token instanceof EmployeeAuthToken) {
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

        } catch(RuntimeException exception) {
            response.sendError(400, exception.getMessage());
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
