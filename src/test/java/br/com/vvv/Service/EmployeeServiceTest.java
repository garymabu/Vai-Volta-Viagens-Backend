package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.DataEmployee;
import br.com.vvv.Domain.DTO.DataRegisterEmployee;
import br.com.vvv.Domain.DTO.DataUpdateEmployee;
import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
public class EmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;
    @Test
    public void testRegisterEmployee() {

        DataRegisterEmployee dataRegisterEmployee = new DataRegisterEmployee(
                "Sylas",
                "Sylas17",
                "DemaciaS2",
                "Rua Noxus",
                "Gerente"

        );

        Employee expectedEmployee = new Employee(dataRegisterEmployee);

        when(employeeRepository.save(any(Employee.class))).thenReturn(expectedEmployee);

        Employee actualEmployee = employeeService.registerEmployee(dataRegisterEmployee);

        assertEquals(expectedEmployee,actualEmployee);
    }
    @Test
    public void testFindByLogin() {

        String login = "teste";

        Employee expectedEmployee = new Employee();

        when(employeeRepository.findByLogin(login)).thenReturn(expectedEmployee);

        Employee result = employeeService.findByLogin(login);

        assertEquals(expectedEmployee, result);
    }

    @Test
    public void testUpdateEmployee(){

        DataUpdateEmployee dataUpdateEmployee = new DataUpdateEmployee(
                "123456789",
                "Draven",
                "Noxus",
                "Gerente",
                "Draven22",
                "Darius"
        );

        Employee existingEmployee = new Employee();

        Employee expectedEmployee = new Employee();

        when(employeeRepository.findById(dataUpdateEmployee.id())).thenReturn(Optional.of(existingEmployee));

        when(employeeRepository.save(existingEmployee)).thenReturn(expectedEmployee);

        Employee actualEmployee = employeeService.updateEmployee(dataUpdateEmployee);

        verify(employeeRepository).findById(dataUpdateEmployee.id());

        verify(employeeRepository).save(existingEmployee);

        assertEquals(expectedEmployee, actualEmployee);

    }
    @Test
    public void testDeleteEmployee(){

        String id = "123456789";

        Employee expectedEmployee = new Employee();

        when(employeeRepository.findById(id)).thenReturn(Optional.of(expectedEmployee));

        employeeService.deleteEmployee(id);

        verify(employeeRepository).findById(id);

        verify(employeeRepository).delete(expectedEmployee);
    }
    @Test
    public void testFindAllEmployees(){

        Pageable pageable = (Pageable) PageRequest.of(0, 10);

        List<Employee> employeeList = new ArrayList<>();

        DataRegisterEmployee dataRegisterEmployee = new DataRegisterEmployee(
                "Katarina",
                "NoxusS2",
                "Shumpo",
                "Noxus",
                "Gerente"
        );

        Employee employee1 = new Employee(dataRegisterEmployee);

        DataRegisterEmployee dataRegisterEmployee2 = new DataRegisterEmployee(
            "Garen",
            "Garen_Demacia",
            "Demacia22",
            "Demacia",
            "Assistente"
        );
        Employee employee2 = new Employee(dataRegisterEmployee);


        employeeList.add(employee1);

        employeeList.add(employee2);

        Page<Employee> pagedResponse = new PageImpl<>(employeeList);

        when(employeeRepository.findAll(any(Pageable.class))).thenReturn(pagedResponse);

        Page<DataEmployee> employees = employeeService.findAllEmployees(pageable);

        verify(employeeRepository, times(1)).findAll(any(Pageable.class));

        assertEquals(employees.getContent().size(), employeeList.size());

        assertTrue(employees.getContent().get(0) instanceof DataEmployee);

    }


}
