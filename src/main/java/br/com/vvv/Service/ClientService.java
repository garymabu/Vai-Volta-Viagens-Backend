package br.com.vvv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.DataRegisterClient;
import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository loginRepository;

    public Client registerClient(DataRegisterClient dataRegisterClient) {
        log.info("[ClientService.registerClient] - [Service]");
        return loginRepository.save(new Client(dataRegisterClient));
    }

    public UserDetails findByLogin(String login) {
        log.info("[ClientService.findByLogin] - [Service]");
        return loginRepository.findByLogin(login);
    }
}