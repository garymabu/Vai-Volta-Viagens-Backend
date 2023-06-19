package br.com.vvv.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.vvv.Domain.DTO.DataBooking;
import br.com.vvv.Domain.DTO.DataRegisterBooking;
import br.com.vvv.Domain.DTO.DataUpdateBooking;
import br.com.vvv.Domain.Entity.Booking;
import br.com.vvv.Repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking creteBooking(DataRegisterBooking dataRegisterBooking) {
        return bookingRepository.save(new Booking(dataRegisterBooking));
    }

    public Page<DataBooking> findAllBooking(Pageable pageable) {
        Page<Booking> bookings = bookingRepository.findAll(pageable);
        return bookings.map(DataBooking::new);
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

        bookingRepository.deleteById(booking.getId());
    }


}
