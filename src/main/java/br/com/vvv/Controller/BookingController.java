package br.com.vvv.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vvv.Domain.DTO.DataBooking;
import br.com.vvv.Domain.DTO.DataRegisterBooking;
import br.com.vvv.Domain.DTO.DataUpdateBooking;
import br.com.vvv.Service.BookingService;
import jakarta.validation.Valid;

@RequestMapping("v1/booking")
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping()
    @Transactional
    public ResponseEntity<String> creteBooking(@RequestBody @Valid DataRegisterBooking dataRegisterBooking) {
        try {
            bookingService.creteBooking(dataRegisterBooking);
            return ResponseEntity.created(null).body("Reserva criada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<Page<DataBooking>> findAllBooking(@PageableDefault(size = 10) Pageable pageable) {
        Page<DataBooking> bookings = bookingService.findAllBooking(pageable);
        return ResponseEntity.ok().body(bookings);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> updateBooking(@PathVariable String id, @RequestBody DataUpdateBooking dataUpdateBooking) {
        try {
            bookingService.updateBooking(id, dataUpdateBooking);
            return ResponseEntity.ok("Reserva atualizada com sucesso");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteBooking(@PathVariable String id) {
        try {
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    
}