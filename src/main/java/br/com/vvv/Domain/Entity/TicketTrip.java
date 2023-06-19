package br.com.vvv.Domain.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "ticket_trip")
@Table(name = "ticket_trip")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String tripid;
    private String idticket;
    
}