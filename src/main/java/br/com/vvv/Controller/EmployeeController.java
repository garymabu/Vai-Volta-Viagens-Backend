package br.com.vvv.Controller;

import br.com.vvv.Domain.DTO.DataBadRequestMessage;
import br.com.vvv.Domain.DTO.DataEmployee;
import br.com.vvv.Domain.DTO.DataRegisterEmployee;
import br.com.vvv.Domain.DTO.DataUpdateEmployee;
import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Service.EmployeeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("v1/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> registerEmployee(@RequestBody @Valid DataRegisterEmployee dataRegisterEmployee) {
        log.info("Registering Employee");
        try {
            employeeService.registerEmployee(dataRegisterEmployee);
        } catch(Exception exc) {
            return ResponseEntity.badRequest().body(
                    new DataBadRequestMessage(exc.getMessage())
            );
        }
        return ResponseEntity.ok(null);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<String> updateEmployee(@RequestBody @Valid DataUpdateEmployee dataUpdateEmployee) {
        log.info("[EmployeeController.updateEmployee] - [Controller]");
        try {
            employeeService.updateEmployee(dataUpdateEmployee);
            return ResponseEntity.ok("Funcionário atualizado com sucesso");
        } catch (IllegalArgumentException iException) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Erro ao tentar atualizar o funcionário: " + ex.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Page<DataEmployee>> findAllEmployees(@PageableDefault(size = 10) Pageable pageable) {
        log.info("[EmployeeController.findAllEmployees] - [Controller]");
        Page<DataEmployee> employees = employeeService.findAllEmployees(pageable);
        return ResponseEntity.ok().body(employees);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteEmployee(@PathVariable String id) {
        log.info("[EmployeeController.deleteEmployee] - [Controller]");
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Funcionário excluído com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar excluir o funcionário: " + e.getMessage());
        }
    }

}