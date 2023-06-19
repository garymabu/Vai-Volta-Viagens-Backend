package br.com.vvv.Domain.Entity;

import java.math.BigDecimal;

import br.com.vvv.Domain.DTO.BookingDTO;
import br.com.vvv.Domain.DTO.DataUpdateBooking;
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

@Entity(name = "booking")
@Table(name = "booking")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer number;
    private Integer timeTrip;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeBooking typeId;

    private String code;
    private boolean layover;
    private String createDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status statusId;

    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "arrival_localization_id")
    private Localization arrivalLocalizationId;

    @ManyToOne
    @JoinColumn(name = "departure_localization_id")
    private Localization departureLocalizationId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;


    public void updateData(DataUpdateBooking dataUpdateBooking) {
        if (dataUpdateBooking.statusId() != null) {
            this.statusId = new Status(dataUpdateBooking.statusId());
        } else {
             throw new IllegalStateException("É necessário fornecer o status para a atualizar.");
        }
    }

    public Booking(BookingDTO bookingDTO) {
        this.id = DataHelper.generatedUuid().toString();
        this.number = bookingDTO.number();
        this.timeTrip = bookingDTO.timeTrip();
        this.typeId = bookingDTO.typeId();
        this.code = bookingDTO.code();
        this.layover = bookingDTO.layover();
        this.createDate = bookingDTO.createDate();
        this.statusId = bookingDTO.statusId();
        this.value = new BigDecimal(bookingDTO.value().toString());
        this.arrivalLocalizationId = bookingDTO.arrivalLocalizationId();
        this.departureLocalizationId = bookingDTO.departureLocalizationId();
        this.clientId = bookingDTO.clientId();
        this.employeeId = bookingDTO.employeeId();
    }

}
