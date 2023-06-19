package br.com.vvv.Domain.Entity;

import br.com.vvv.Helpers.DataHelper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "ticket")
@Table(name = "ticket")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking bookingId;

    public Ticket(Booking booking) {
        this.id = DataHelper.generatedUuid().toString();
        this.bookingId = booking;
    }
}