package br.com.vvv.Domain.Entity;

import java.math.BigDecimal;

import br.com.vvv.Domain.DTO.DataRegisterBooking;
import br.com.vvv.Domain.DTO.DataUpdateBooking;
import br.com.vvv.Helpers.DataHelper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String typeId;
    private String code;
    private boolean layover;
    private String createDate;
    private boolean status;
    private BigDecimal value;
    private String originId;
    private String arrivalLocalization;
    private String departureLocalization;
    private String clientId;
    private String employeeId;


    public Booking(DataRegisterBooking dataRegisterBooking) {
        this.id = DataHelper.generatedUuid().toString();
        this.number = dataRegisterBooking.number();
        this.timeTrip = dataRegisterBooking.timeTrip();
        this.typeId = dataRegisterBooking.typeId();
        this.code = dataRegisterBooking.code();
        this.layover = dataRegisterBooking.layover();
        this.createDate = dataRegisterBooking.createDate();
        this.status = dataRegisterBooking.status();
        this.value = new BigDecimal(dataRegisterBooking.value().toString());
        this.originId = dataRegisterBooking.originId();
        this.arrivalLocalization = dataRegisterBooking.arrivalLocalization();
        this.departureLocalization = dataRegisterBooking.departureLocalization();
        this.clientId = dataRegisterBooking.clientId();
        this.employeeId = dataRegisterBooking.employeeId();
    }


    public void updateData(DataUpdateBooking dataUpdateBooking) {
        if (dataUpdateBooking.number() != null) {
            this.number = dataUpdateBooking.number();
        }

        if (dataUpdateBooking.timeTrip() != null) {
            this.timeTrip = dataUpdateBooking.timeTrip();
        }

        if (dataUpdateBooking.typeId() != null) {
            this.typeId = dataUpdateBooking.typeId();
        }

        if (dataUpdateBooking.code() != null) {
            this.code = dataUpdateBooking.code();
        }

        if (String.valueOf(dataUpdateBooking.layover()) != null) {
            this.layover = dataUpdateBooking.layover();
        }

        if (dataUpdateBooking.createDate() != null) {
            this.createDate = dataUpdateBooking.createDate();
        }

        if (String.valueOf(dataUpdateBooking.status()) != null) {
            this.status = dataUpdateBooking.status();
        }

        if (dataUpdateBooking.value() != null) {
            this.value = new BigDecimal(dataUpdateBooking.value().toString());
        }

        if (dataUpdateBooking.originId() != null) {
            this.originId = dataUpdateBooking.originId();
        }

        if (dataUpdateBooking.arrivalLocalization() != null) {
            this.arrivalLocalization = dataUpdateBooking.arrivalLocalization();
        }

        if (dataUpdateBooking.departureLocalization() != null) {
            this.departureLocalization = dataUpdateBooking.departureLocalization();
        }

        if (dataUpdateBooking.clientId() != null) {
            this.clientId = dataUpdateBooking.clientId();
        }

        if (dataUpdateBooking.employeeId() != null) {
            this.employeeId = dataUpdateBooking.employeeId();
        }

        if (dataUpdateBooking.number() == null && dataUpdateBooking.timeTrip() == null &&
                dataUpdateBooking.typeId() == null && dataUpdateBooking.code() == null &&
                String.valueOf(dataUpdateBooking.layover()) == null && dataUpdateBooking.createDate() == null &&
                String.valueOf(dataUpdateBooking.status()) == null && dataUpdateBooking.value() == null &&
                dataUpdateBooking.originId() == null && dataUpdateBooking.arrivalLocalization() == null &&
                dataUpdateBooking.departureLocalization() == null && dataUpdateBooking.clientId() == null &&
                dataUpdateBooking.employeeId() == null) {
            throw new IllegalStateException("É necessário fornecer pelo menos um valor para a atualização.");
        }
    }

}
