package br.com.vvv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vvv.Domain.Entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, String>{

}
