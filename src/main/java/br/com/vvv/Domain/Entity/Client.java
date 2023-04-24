package br.com.vvv.Domain.Entity;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.vvv.Domain.DTO.DataRegisterClient;
import br.com.vvv.Helpers.DataHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "client")
@Table(name = "client")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String name;
  private Integer age;
  private String login;

  @Column(name = "password")
  @ColumnTransformer(
    read = "AES_DECRYPT(password, 'vvv@369528')",
    write = "AES_ENCRYPT(?, 'vvv@369528')"
  )
  private String password;
  private String cpf;
  private String profession;
  private String tell;

  public Client(DataRegisterClient dataRegisterClient) {
    this.id = DataHelper.generatedUuid().toString();
    this.name = dataRegisterClient.name();
    this.age = dataRegisterClient.age();
    this.login = dataRegisterClient.login();
    this.password = dataRegisterClient.password();
    this.cpf = dataRegisterClient.cpf();
    this.profession = dataRegisterClient.profession();
    this.tell = dataRegisterClient.tell();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return password;
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
}