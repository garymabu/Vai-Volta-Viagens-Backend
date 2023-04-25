package br.com.vvv.Service;

import br.com.vvv.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.AuthToken;
import br.com.vvv.Domain.Entity.Client;

@Service
public class ProfileService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findProfile(String token) {
        AuthToken authToken = new AuthToken(token);
        var clientLogin = authToken.getClientLogin();
        return clientRepository.findByLogin(clientLogin);
    }
}