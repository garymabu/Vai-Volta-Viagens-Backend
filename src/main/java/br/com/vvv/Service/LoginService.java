package br.com.vvv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.dto.DataRegisterClient;
import br.com.vvv.Domain.entity.Client;
import br.com.vvv.Repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;
    
    public void registerClient(DataRegisterClient dataRegisterClient) {
        loginRepository.save(new Client(dataRegisterClient));
        return;
    }
}