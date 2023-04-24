package br.com.vvv.Config;
import br.com.vvv.Domain.DTO.AuthToken;
import br.com.vvv.Domain.DTO.DataBadRequestMessage;
import br.com.vvv.Service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {
    private final String[] unfilteredRoutes = {
        "/v1/auth",
        "/v1/client"
    };
    @Autowired
    private ClientService clientService;

    private HttpServletResponse tokenValidation(HttpServletRequest request, HttpServletResponse response)
            throws java.io.IOException {
        try {
            var token = AuthToken.fromRequest(request);
            var login = token.getClientLogin();
            var client = clientService.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(
                    client,
                    null,
                    client.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(RuntimeException exception ) {
            response.sendError(400, exception.getMessage());
        }
        return response;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws java.io.IOException, ServletException {
        String path = request.getRequestURI();
        if(Arrays.stream(unfilteredRoutes).anyMatch(s -> s.equals(request.getPathInfo()))) {
            response = tokenValidation(request,response);
        }
        filterChain.doFilter(request, response);
    }
}