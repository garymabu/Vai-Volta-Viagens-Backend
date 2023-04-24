package br.com.vvv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.vvv.Domain.Entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    UserDetails findByLogin(String subject);
    
}