package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.EmployeeAuthToken;
import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Repository.EmployeeAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import br.com.vvv.Domain.DTO.DataToLogin;

@Service
@Slf4j
public class EmployeeAuthService {
    @Autowired
    private EmployeeAuthRepository employeeAuthRepository;

    public String login(DataToLogin dataToLogin) {
        log.info("[AuthEmployeeService]");
        Employee employee = employeeAuthRepository.findByLoginAndPassword(dataToLogin.login(), dataToLogin.password());

        if (employee == null) {
            throw new BadCredentialsException("Invalid Credentials");
        }

        String token = new EmployeeAuthToken(employee).getContent();
        return token;
    }
}
