package br.com.vvv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vvv.Domain.Entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

}
