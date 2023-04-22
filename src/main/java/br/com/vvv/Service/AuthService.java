package br.com.vvv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.dto.DataToLogin;
import br.com.vvv.Domain.entity.Client;
import br.com.vvv.Repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {
    
    @Autowired
    AuthRepository authRepository;

    @Autowired
    TokenService tokenService;

    public String login(DataToLogin dataToLogin) {
        log.info("[AuthService]");
        Client client = authRepository.findByLoginAndPassword(dataToLogin.login(), dataToLogin.password());

        if(client == null || !dataToLogin.password().equals(client.getPassword()))
            return null;

        String token = tokenService.generateToken(client);
        return token;
    }
    
}