package br.com.vvv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vvv.Domain.entity.Client;

@Repository
public interface LoginRepository extends JpaRepository<Client, String> {
    
}