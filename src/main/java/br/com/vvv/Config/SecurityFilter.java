package br.com.vvv.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.vvv.Service.ClientService;
import br.com.vvv.Service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClientService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws java.io.IOException, ServletException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            log.info("[SecurityFilter.doFilterInternal] - [token != null]");
            var subject = tokenService.getSubject(tokenJWT);
            var client = service.findByLogin(subject);

            var authentication = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        log.info("[SecurityFilter.recuperarToken] - [recuperando token]");
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            log.info("[SecurityFilter.recuperarToken] - [replace token]");
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}