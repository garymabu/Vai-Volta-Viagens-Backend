package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.DataRegisterEmployee;
import br.com.vvv.Domain.DTO.DataToLogin;
import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Repository.EmployeeAuthRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeAuthServiceTest {

    @InjectMocks
    private EmployeeAuthService employeeAuthService;

    @Mock
    private EmployeeAuthRepository employeeAuthRepository;

    @Test
    public void loginSucessTest(){

        DataToLogin dataToLogin = new DataToLogin("Cogumelo", "melo");

        DataRegisterEmployee dataRegisterEmployee = new DataRegisterEmployee(
                "Sylas",
                "Sylas17",
                "DemaciaS2",
                "Rua Noxus",
                "Gerente"

        );

        Employee expectedEmployee = new Employee(dataRegisterEmployee);

        when(employeeAuthRepository.findByLoginAndPassword(dataToLogin.login(), dataToLogin.password())).thenReturn(expectedEmployee);

        String actualToken = employeeAuthService.login(dataToLogin);

        verify(employeeAuthRepository).findByLoginAndPassword(dataToLogin.login(), dataToLogin.password());

        assertNotNull(actualToken);
        assertFalse(actualToken.isEmpty());

    }
    @Test
    public void loginFailureTest(){

        DataToLogin dataToLogin = new DataToLogin("CogumeloErrado", "meloErrado");

        when(employeeAuthRepository.findByLoginAndPassword(dataToLogin.login(), dataToLogin.password())).thenReturn(null);

        assertThrows(BadCredentialsException.class, () ->{
            employeeAuthService.login(dataToLogin);
        });

        verify(employeeAuthRepository).findByLoginAndPassword(dataToLogin.login(), dataToLogin.password());
    }
}
