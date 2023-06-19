package br.com.vvv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.DataRegisterEmployee;
import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee registerEmployee(DataRegisterEmployee dataRegisterEmployee) {
        log.info("[EmployeeService.registerEmployee] - [Service]");
        Employee employee = new Employee(dataRegisterEmployee);
        return employeeRepository.save(employee);
    }

    public Employee findByLogin(String login) {
        log.info("[EmployeeService.findByLogin] - [Service]");
        return employeeRepository.findByLogin(login);
    }
}
