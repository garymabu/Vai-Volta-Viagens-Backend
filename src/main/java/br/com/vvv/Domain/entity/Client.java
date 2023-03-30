package br.com.vvv.Domain.entity;

import br.com.vvv.Domain.dto.DataRegisterClient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "client")
@Table(name = "client")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    public Client(DataRegisterClient dataRegisterClient) {
        this.name = dataRegisterClient.name();
        this.age = dataRegisterClient.age();
        this.login = dataRegisterClient.login();
        this.password = dataRegisterClient.password();
        this.cpf = dataRegisterClient.cpf();
        this.profession = dataRegisterClient.profession();
        this.tell = dataRegisterClient.tell();
    }

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
    
}