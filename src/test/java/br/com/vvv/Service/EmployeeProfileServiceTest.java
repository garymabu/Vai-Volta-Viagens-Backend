package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.DataRegisterEmployee;
import br.com.vvv.Domain.DTO.EmployeeAuthToken;
import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class EmployeeProfileServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeProfileService employeeProfileService;

    @Test
    public void testFindProfile(){

        DataRegisterEmployee dataRegisterEmployee = new DataRegisterEmployee(
                "Sylas",
                "Sylas17",
                "DemaciaS2",
                "Rua Noxus",
                "Gerente"

        );

        Employee expectedEmployee = new Employee(dataRegisterEmployee);

        when(employeeRepository.findByLogin(any(String.class))).thenReturn(expectedEmployee);

        EmployeeAuthToken employeeAuthToken = new EmployeeAuthToken(expectedEmployee);
        String token = employeeAuthToken.getContent();

        Employee actualEmployee = employeeProfileService.findProfile(token);

        verify(employeeRepository).findByLogin(expectedEmployee.getLogin());

        assertEquals(expectedEmployee, actualEmployee);



    }


}
