package br.com.vvv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.dto.DadosTokenJWT;
import br.com.vvv.Domain.dto.DataBadRequestMessage;
import br.com.vvv.Domain.dto.DataToLogin;
import br.com.vvv.Service.AuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid DataToLogin dataToLogin) {
        log.info("[AuthController]");
        String string = authService.login(dataToLogin);

        if(string == null)
            return ResponseEntity.badRequest().body(new DataBadRequestMessage("Username or Password is Invalid"));

        return ResponseEntity.ok().body(new DadosTokenJWT(string));
    }
    
}