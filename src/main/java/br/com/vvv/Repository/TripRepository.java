package br.com.vvv.Repository;

import br.com.vvv.Domain.Entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, String> {

}
