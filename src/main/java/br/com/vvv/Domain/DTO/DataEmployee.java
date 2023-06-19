package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Employee;

public record DataEmployee(
        String id,
        String name,
        String address,
        String type,
        String login,
        String password
) {

    public DataEmployee(Employee employee) {
        this(employee.getId(), employee.getName(), employee.getAddress(), employee.getType(), employee.getLogin(), employee.getPassword());
    }

}