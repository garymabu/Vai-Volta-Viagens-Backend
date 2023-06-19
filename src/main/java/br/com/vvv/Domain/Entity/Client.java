package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.DataRegisterClient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity(name = "client")
@Table(name = "client")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends User {

  private String name;
  private Integer age;
  private String cpf;
  private String profession;
  private String tell;

  public Client(DataRegisterClient dataRegisterClient) {
    super(UUID.randomUUID().toString(), dataRegisterClient.login(), dataRegisterClient.password());
    this.name = dataRegisterClient.name();
    this.age = dataRegisterClient.age();
    this.cpf = dataRegisterClient.cpf();
    this.profession = dataRegisterClient.profession();
    this.tell = dataRegisterClient.tell();
  }
}
