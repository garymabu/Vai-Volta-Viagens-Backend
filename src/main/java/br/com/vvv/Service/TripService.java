package br.com.vvv.Service;

import br.com.vvv.Domain.DTO.DataProfileTrip;
import br.com.vvv.Domain.DTO.DataRegisterTrip;
import br.com.vvv.Domain.DTO.TravelsWithLayoverDTO;
import br.com.vvv.Domain.DTO.TripDTO;
import br.com.vvv.Domain.Entity.Localization;
import br.com.vvv.Domain.Entity.Trip;
import br.com.vvv.Repository.LocalizationRepository;
import br.com.vvv.Repository.TripRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<TravelsWithLayoverDTO> getAllTripsWithLayovers(
            String localizationDepartureId,
            String localizationDestinationId
    ) {
        log.info("[TripService.GetAllTripsWithLayovers] - [Service]");
        Localization departureLocalization = localizationRepository.findById(localizationDepartureId)
                .orElseThrow(() -> new EntityNotFoundException("Localization not found with id: " + localizationDepartureId));
        Localization destinationLocalization = localizationRepository.findById(localizationDestinationId)
                .orElseThrow(() -> new EntityNotFoundException("Localization not found with id: " + localizationDestinationId));

        List<Trip> allTrips = tripRepository.findAll();

        List<TravelsWithLayoverDTO> result = new ArrayList<>();
        getTripsWithLayovers(departureLocalization, destinationLocalization, allTrips, new ArrayList<>(), result);
        return result;
    }

    private void getTripsWithLayovers(Localization currentLocalization, Localization destinationLocalization, List<Trip> allTrips, List<Trip> currentTrip, List<TravelsWithLayoverDTO> result) {
        if (currentLocalization.equals(destinationLocalization)) {
            if (!currentTrip.isEmpty()) {
                DataProfileTrip firstTrip = new DataProfileTrip(currentTrip.get(0));
                DataProfileTrip lastTrip = new DataProfileTrip(currentTrip.get(currentTrip.size() - 1));
                List<DataProfileTrip> tripsUntilDestination = new ArrayList<>();
                for (Trip trip : currentTrip) {
                    tripsUntilDestination.add(new DataProfileTrip(trip));
                }
                Boolean containsLayover = tripsUntilDestination.size() > 1;
                result.add(new TravelsWithLayoverDTO(tripsUntilDestination, firstTrip, lastTrip, containsLayover));
            }
            return;
        }

        for (Trip trip : allTrips) {
            if (trip.getDepartureLocalization().equals(currentLocalization)
                    && !currentTrip.contains(trip)
                    && (currentTrip.isEmpty() || trip.getDepartureDatetime().isAfter(currentTrip.get(currentTrip.size() - 1).getArrivalDatetime()))) {
                currentTrip.add(trip);
                getTripsWithLayovers(trip.getArrivalLocalization(), destinationLocalization, allTrips, currentTrip, result);
                currentTrip.remove(trip);
            }
        }
    }
}
