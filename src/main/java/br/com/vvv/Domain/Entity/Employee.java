package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.DataRegisterEmployee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "employee")
@Table(name = "employee")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {

    private String name;
    private String address;
    private String type;

    public Employee(DataRegisterEmployee dataRegisterEmployee) {
        super(UUID.randomUUID().toString(),dataRegisterEmployee.login(), dataRegisterEmployee.password());
        this.name = dataRegisterEmployee.name();
        this.address = dataRegisterEmployee.address();
        this.type = dataRegisterEmployee.type();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(type));
    }
}
