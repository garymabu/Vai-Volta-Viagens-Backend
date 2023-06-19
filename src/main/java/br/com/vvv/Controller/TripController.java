package br.com.vvv.Controller;

import br.com.vvv.Domain.DTO.*;
import br.com.vvv.Domain.Entity.Trip;
import br.com.vvv.Service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trip")
public class TripController {
    @Autowired
    TripService tripService;
    @PostMapping
    public ResponseEntity<?> registerTrip(@RequestBody @Valid DataRegisterTrip dataRegisterTrip) {
        try {
            var generatedTrip = tripService.registerTrip(dataRegisterTrip);
            return ResponseEntity.ok().body(new DataProfileTrip(generatedTrip));
        } catch(Exception exc) {
            return ResponseEntity.badRequest().body(
                new DataBadRequestMessage(exc.getMessage())
            );
        }
    }
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        var trips = tripService.getAllTrips();
        return ResponseEntity.ok(trips);
    }

    @GetMapping("/match-with-layover")
    public ResponseEntity<List<TravelsWithLayoverDTO>> getAllTripsBetweenPlaces(
        @RequestParam("departureLocalizationId") String localizationDepartureId,
        @RequestParam("destinationLocalizationId") String localizationDestinationId
    ) {
        var result = tripService.getAllTripsWithLayovers(
            localizationDepartureId,
            localizationDestinationId
        );
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable String id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build(); // HTTP 204 on success
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable String id, @RequestBody DataRegisterTrip updatedData) {
        Trip updatedTrip = tripService.updateTrip(id, updatedData);
        return ResponseEntity.ok(updatedTrip); // HTTP 200 on success
    }
}
