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
import br.com.vvv.Service.EmployeeAuthService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/auth/employee")
@Slf4j
public class EmployeeAuthController {

    @Autowired
    private EmployeeAuthService employeeAuthService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid DataToLogin dataToLogin) {
        log.info("[EmployeeAuthController]");
        try {
            String string = employeeAuthService.login(dataToLogin);
            return ResponseEntity.ok().body(new DadosTokenJWT(string));
        }
        catch(Exception exc) {
            return ResponseEntity.badRequest().body(
                    new DataBadRequestMessage("Username or Password is Invalid")
            );
        }
    }
}
