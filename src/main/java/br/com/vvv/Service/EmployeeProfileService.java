package br.com.vvv.Service;

import br.com.vvv.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.EmployeeAuthToken;
import br.com.vvv.Domain.Entity.Employee;

@Service
public class EmployeeProfileService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findProfile(String token) {
        EmployeeAuthToken authToken = new EmployeeAuthToken(token);
        var employeeLogin = authToken.getUserLogin();
        return employeeRepository.findByLogin(employeeLogin);
    }
}
