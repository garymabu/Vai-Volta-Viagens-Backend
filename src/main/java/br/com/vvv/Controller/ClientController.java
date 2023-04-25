package br.com.vvv.Controller;

import br.com.vvv.Domain.DTO.DataBadRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.DTO.DataRegisterClient;
import br.com.vvv.Service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/client")
@Slf4j
public class ClientController {
    @Autowired
    ClientService loginService;

    @PostMapping
    public ResponseEntity<?> registerClient(@RequestBody @Valid DataRegisterClient dataRegisterClient) {
        log.info("Controller");
        try {
            loginService.registerClient(dataRegisterClient);
        } catch(Exception exc) {
            return ResponseEntity.badRequest().body(
                new DataBadRequestMessage(exc.getMessage())
            );
        }
        return ResponseEntity.ok(null);
    }
}