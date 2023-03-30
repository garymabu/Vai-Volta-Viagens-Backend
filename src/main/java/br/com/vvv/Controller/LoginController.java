package br.com.vvv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.dto.DataRegisterClient;
import br.com.vvv.Service.LoginService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping
@RestController("v1/login")
@Slf4j
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/register_client")
    public ResponseEntity<Void> registerClient(@RequestBody @Valid DataRegisterClient dataRegisterClient) {
        log.info("DTO: " + dataRegisterClient);
        return ResponseEntity.ok(null);
    }
}