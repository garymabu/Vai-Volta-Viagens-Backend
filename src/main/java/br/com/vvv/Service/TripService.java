package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.DataRegisterTrip;
import br.com.vvv.Domain.DTO.TripDTO;
import br.com.vvv.Domain.Entity.Localization;
import br.com.vvv.Domain.Entity.Trip;
import br.com.vvv.Repository.LocalizationRepository;
import br.com.vvv.Repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class TripService {
    @Autowired
    TripRepository tripRepository;
    @Autowired
    LocalizationRepository localizationRepository;
    private TripDTO toTripDTO(DataRegisterTrip dataRegisterTrip) {
        Localization arrivalLocalization = localizationRepository.findById(dataRegisterTrip.arrivalLocalizationId())
                .orElseThrow(() -> new EntityNotFoundException("Localization not found with id: " + dataRegisterTrip.arrivalLocalizationId()));

        Localization departureLocalization = localizationRepository.findById(dataRegisterTrip.departureLocalizationId())
                .orElseThrow(() -> new EntityNotFoundException("Localization not found with id: " + dataRegisterTrip.departureLocalizationId()));

        return new TripDTO(
            dataRegisterTrip.arrivalDatetime(),
            dataRegisterTrip.departureDatetime(),
            arrivalLocalization,
            departureLocalization
        );
    }

    public Trip registerTrip(DataRegisterTrip dataRegisterTrip) {
        log.info("[TripService.RegisterTrip] - [Service]");
        return tripRepository.save(new Trip(toTripDTO(dataRegisterTrip)));
    }
    public List<Trip> getAllTrips() {
        log.info("[TripService.GetAllTrips] - [Service]");
        return tripRepository.findAll();
    }
    public void deleteTrip(String id) {
        log.info("[TripService.DeleteTrip] - [Service]");
        tripRepository.deleteById(id);
    }

    public Trip updateTrip(String id, DataRegisterTrip updatedData) {
        log.info("[TripService.UpdateTrip] - [Service]");
        Trip existingTrip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));

        existingTrip.updateData(id, toTripDTO(updatedData));

        return tripRepository.save(existingTrip);
    }
}
