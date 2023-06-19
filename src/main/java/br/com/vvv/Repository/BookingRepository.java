package br.com.vvv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vvv.Domain.Entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, String> {

}
