package br.com.vvv.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.BookingDTO;
import br.com.vvv.Domain.DTO.DataRegisterBooking;
import br.com.vvv.Domain.DTO.DataUpdateBooking;
import br.com.vvv.Domain.Entity.Booking;
import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Domain.Entity.Localization;
import br.com.vvv.Domain.Entity.Status;
import br.com.vvv.Domain.Entity.Ticket;
import br.com.vvv.Domain.Entity.TypeBooking;
import br.com.vvv.Repository.BookingRepository;
import br.com.vvv.Repository.ClientRepository;
import br.com.vvv.Repository.EmployeeRepository;
import br.com.vvv.Repository.LocalizationRepository;
import br.com.vvv.Repository.StatusRepository;
import br.com.vvv.Repository.TicketRepository;
import br.com.vvv.Repository.TypeBookingRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private LocalizationRepository localizationRepository;
    
    @Autowired
    private TypeBookingRepository typeBookingRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired 
    private EmployeeRepository employeeRepository;

    private BookingDTO toBookingDTO(DataRegisterBooking dataRegisterBooking) {
        Localization arrivalLocalization = localizationRepository.findById(dataRegisterBooking.arrivalLocalizationId())
            .orElseThrow(() -> new EntityNotFoundException("Localization not found with id: " + dataRegisterBooking.arrivalLocalizationId()));

        Localization departureLocalization = localizationRepository.findById(dataRegisterBooking.departureLocalizationId())
                .orElseThrow(() -> new EntityNotFoundException("Localization not found with id: " + dataRegisterBooking.departureLocalizationId()));

        TypeBooking typeBooking = typeBookingRepository.findById(dataRegisterBooking.typeId())
                .orElseThrow(() -> new EntityNotFoundException("TypeBooking not found with id: " + dataRegisterBooking.typeId()));

        Status status = statusRepository.findById(dataRegisterBooking.statusId())
                .orElseThrow(() -> new EntityNotFoundException("Status not found with id: " + dataRegisterBooking.statusId()));

        Client client = clientRepository.findById(dataRegisterBooking.clientId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + dataRegisterBooking.clientId()));

        Employee employee = employeeRepository.findById(dataRegisterBooking.employeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + dataRegisterBooking.employeeId()));

        return new BookingDTO(
            dataRegisterBooking.number(),
            dataRegisterBooking.timeTrip(),
            typeBooking,
            dataRegisterBooking.code(),
            dataRegisterBooking.layover(),
            dataRegisterBooking.createDate(),
            status,
            new BigDecimal(dataRegisterBooking.value()),
            arrivalLocalization,
            departureLocalization,
            client,
            employee
        );
    }

    public Booking creteBooking(DataRegisterBooking dataRegisterBooking) {
        Booking booking = new Booking(toBookingDTO(dataRegisterBooking));
        booking = bookingRepository.save(booking);

        Ticket ticket = new Ticket(booking);
        ticketRepository.save(ticket);

        return booking;
    }

    public List<Booking> findAllBooking() {
        return bookingRepository.findAll();
    }

    public Booking updateBooking(String id, DataUpdateBooking dataUpdateBooking) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));

        booking.updateData(dataUpdateBooking);

        return bookingRepository.save(booking);
    }

    public void deleteBooking(String id) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));

        Localization arrivalLocalization = localizationRepository.findById(booking.getArrivalLocalizationId())
            .orElseThrow(() -> new EntityNotFoundException("Localization not found with id: " + booking.getArrivalLocalizationId()));

        Localization departureLocalization = localizationRepository.findById(booking.getDepartureLocalizationId())
            .orElseThrow(() -> new EntityNotFoundException("Localization not found with id: " + booking.getDepartureLocalizationId()));

        TypeBooking typeBooking = typeBookingRepository.findById(booking.getTypeId().toString())
            .orElseThrow(() -> new EntityNotFoundException("TypeBooking not found with id: " + booking.getTypeId()));

        Status status = statusRepository.findById(booking.getStatusId().toString())
            .orElseThrow(() -> new EntityNotFoundException("Status not found with id: " + booking.getStatusId()));

        ticketRepository.deleteById(booking.getId());

        bookingRepository.deleteById(booking.getId());

        localizationRepository.deleteById(arrivalLocalization.getId());

        localizationRepository.deleteById(departureLocalization.getId());

        typeBookingRepository.deleteById(typeBooking.getId());

        statusRepository.deleteById(status.getId());

        ticketRepository.deleteById(id);

        bookingRepository.deleteById(booking.getId());
    }


}
