package br.com.vvv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.DTO.DadosTokenJWT;
import br.com.vvv.Domain.DTO.DataBadRequestMessage;
import br.com.vvv.Domain.DTO.DataToLogin;
import br.com.vvv.Service.ClientAuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/auth/client")
@Slf4j
public class ClientAuthController {

    @Autowired
    private ClientAuthService clientAuthService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid DataToLogin dataToLogin) {
        log.info("[ClientAuthController]");
        try {
            String string = clientAuthService.login(dataToLogin);
            return ResponseEntity.ok().body(new DadosTokenJWT(string));
        }
        catch(Exception exc) {
            return ResponseEntity.badRequest().body(
                    new DataBadRequestMessage("Username or Password is Invalid")
            );
        }
    }
}
