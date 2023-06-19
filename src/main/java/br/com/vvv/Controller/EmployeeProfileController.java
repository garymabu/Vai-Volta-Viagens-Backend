package br.com.vvv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.DTO.DataProfileEmployee;
import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Service.EmployeeProfileService;

@RestController
@RequestMapping("/v1/profile/employee")
public class EmployeeProfileController {

    @Autowired
    private EmployeeProfileService employeeProfileService;

    @GetMapping("")
    public ResponseEntity<DataProfileEmployee> findEmployeeProfile(@RequestHeader("Authorization") String authentication) {
        Employee profile = employeeProfileService.findProfile(authentication.replace("Bearer ", ""));
        return ResponseEntity.ok().body(new DataProfileEmployee(profile));
    }
}
