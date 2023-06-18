package br.com.vvv.Repository;

import br.com.vvv.Domain.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeAuthRepository extends JpaRepository<Employee, String> {
    Employee findByLoginAndPassword(String login, String password);
}
