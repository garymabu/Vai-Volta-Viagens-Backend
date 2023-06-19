package br.com.vvv.Domain.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@MappedSuperclass
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements UserDetails {

    @Id
    protected String id;
    protected String login;
    @Column(name = "password")
    @ColumnTransformer(
            read = "AES_DECRYPT(password, 'vvv@369528')",
            write = "AES_ENCRYPT(?, 'vvv@369528')"
    )
    protected String password;

    public User(String id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    protected void setLogin(String login) {
        this.login = login;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
}
