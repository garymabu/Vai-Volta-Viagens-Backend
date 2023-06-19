package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.DataUpdateBooking;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "status")
@Table(name = "status")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    public void updateData(DataUpdateBooking dataUpdateBooking) {
        if (dataUpdateBooking.statusId() != null) {
            this.id = dataUpdateBooking.statusId();
        } else {
             throw new IllegalStateException("É necessário fornecer o status para a atualizar.");
        }
    }

    public Status(String statusId2) {
        this.id = statusId2;
    }
}