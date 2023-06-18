package br.com.vvv.Controller;

import br.com.vvv.Domain.DTO.DataBadRequestMessage;
import br.com.vvv.Domain.DTO.DataRegisterEmployee;
import br.com.vvv.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> registerEmployee(@RequestBody @Valid DataRegisterEmployee dataRegisterEmployee) {
        log.info("Employee Controller");
        try {
            employeeService.registerEmployee(dataRegisterEmployee);
        } catch(Exception exc) {
            return ResponseEntity.badRequest().body(
                    new DataBadRequestMessage(exc.getMessage())
            );
        }
        return ResponseEntity.ok(null);
    }
}
