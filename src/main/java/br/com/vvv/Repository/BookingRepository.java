package br.com.vvv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vvv.Domain.Entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

}
