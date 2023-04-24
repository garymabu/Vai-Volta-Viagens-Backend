package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.DataToLogin;
import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
    @Autowired
    AuthRepository authRepository;
    public String login(DataToLogin dataToLogin) {
        log.info("[AuthService]");
        Client client = authRepository.findByLoginAndPassword(
                dataToLogin.login(),
                dataToLogin.password()
        );

        if (client == null || !dataToLogin.password().equals(client.getPassword()))
            throw new BadCredentialsException("Invalid Credentials");

        String token = new AuthToken(client).getContent();
        return token;
    }
}