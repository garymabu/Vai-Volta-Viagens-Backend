package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Employee;

public record DataProfileEmployee(
        String name,
        String login,
        String address,
        String type
) {

    public DataProfileEmployee(Employee employee) {
        this(employee.getName(),employee.getLogin(), employee.getAddress(), employee.getType());
    }

}
