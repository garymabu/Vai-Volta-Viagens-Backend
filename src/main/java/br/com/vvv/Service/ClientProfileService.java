package br.com.vvv.Service;

import br.com.vvv.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.ClientAuthToken;
import br.com.vvv.Domain.Entity.Client;

@Service
public class ClientProfileService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findProfile(String token) {
        ClientAuthToken authToken = new ClientAuthToken(token);
        var clientLogin = authToken.getUserLogin();
        return clientRepository.findByLogin(clientLogin);
    }
}
