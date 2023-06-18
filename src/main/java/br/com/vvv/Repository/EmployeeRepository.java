package br.com.vvv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.vvv.Domain.Entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByLogin(String login);
}
