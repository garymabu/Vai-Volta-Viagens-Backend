package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.ClientAuthToken;
import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Repository.ClientAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import br.com.vvv.Domain.DTO.DataToLogin;

@Service
@Slf4j
public class ClientAuthService {
    @Autowired
    private ClientAuthRepository authClientRepository;

    public String login(DataToLogin dataToLogin) {
        log.info("[AuthClientService]");
        Client client = authClientRepository.findByLoginAndPassword(dataToLogin.login(), dataToLogin.password());

        if (client == null) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        String token = new ClientAuthToken(client).getContent();
        return token;
    }
}
