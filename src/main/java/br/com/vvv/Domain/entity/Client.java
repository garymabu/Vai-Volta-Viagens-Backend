package br.com.vvv.Domain.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.vvv.Domain.dto.DataRegisterClient;
import br.com.vvv.Helpers.DataHelpers;
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
  private String password;
  private String cpf;
  private String profession;
  private String tell;

  public Client(DataRegisterClient dataRegisterClient) {
    this.id = DataHelpers.generatedUuid().toString();
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