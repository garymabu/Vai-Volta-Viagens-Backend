package br.com.vvv.Repository;

import br.com.vvv.Domain.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAuthRepository extends JpaRepository<Client, String> {
    Client findByLoginAndPassword(String login, String password);
}
