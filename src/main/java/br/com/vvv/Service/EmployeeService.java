package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.DataEmployee;
import br.com.vvv.Domain.DTO.DataUpdateEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Employee updateEmployee(DataUpdateEmployee dataUpdateEmployee) {
        log.info("[EmployeeService.updateEmployee] - [Service]");
        Employee employee = employeeRepository.findById(dataUpdateEmployee.id())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        employee.updateData(dataUpdateEmployee);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {
        log.info("[EmployeeService.deleteEmployee] - [Service]");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
        employeeRepository.delete(employee);
    }

    public Page<DataEmployee> findAllEmployees(Pageable pageable) {
        log.info("[EmployeeService.findAllEmployees] - [Service]");
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(DataEmployee::new);
    }


}
