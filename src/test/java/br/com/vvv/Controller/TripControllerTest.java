package br.com.vvv.Controller;

import br.com.vvv.Domain.DTO.DataRegisterLocalization;
import br.com.vvv.Domain.DTO.DataRegisterTrip;
import br.com.vvv.Domain.Entity.Localization;
import br.com.vvv.Domain.Entity.Trip;
import br.com.vvv.Service.TripService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static br.com.vvv.TestUtils.DataManipulationUtil.asJsonString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TripControllerTest {
    @Mock
    private TripService tripService;

    @InjectMocks
    private TripController tripController;

    private Trip mockedTrip;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Create a mocked Trip object using the constructor
        LocalDateTime arrivalDatetime = LocalDateTime.of(2023, 6, 22, 14, 30);
        LocalDateTime departureDatetime = LocalDateTime.of(2023, 6, 20, 8, 30);
        Float tripValue = 200.0f;
        Localization arrivalLocalization = new Localization(
            new DataRegisterLocalization(
                "mcy",
                "mca",
                "mock",
                "mock city"
            )
        );
        Localization departureLocalization = new Localization(
            new DataRegisterLocalization(
                "mcy",
                "mca",
                "mock",
                "mock city"
            )
        );

        mockedTrip = new Trip("12345", arrivalDatetime, departureDatetime,
        arrivalLocalization, departureLocalization, tripValue);
    }
    @Test
    void registerTrip() {
        LocalDateTime arrivalDatetime = LocalDateTime.of(2023, 6, 22, 14, 30);
        LocalDateTime departureDatetime = LocalDateTime.of(2023, 6, 20, 8, 30);
        Float tripValue = 200.0f;
        var arrivalLocalizationId = "a";
        var departureLocalizationId = "b";

        DataRegisterTrip dataRegisterTrip = new DataRegisterTrip(
            arrivalDatetime,
            departureDatetime,
            arrivalLocalizationId,
            departureLocalizationId,
            tripValue
        );


        // Mock the service call to return the mocked Trip object
        when(tripService.registerTrip(any(DataRegisterTrip.class))).thenReturn(mockedTrip);

        var response = tripController.registerTrip(dataRegisterTrip);

        // Verify the service method was called with the correct arguments
        ArgumentCaptor<DataRegisterTrip> captor = ArgumentCaptor.forClass(DataRegisterTrip.class);
        verify(tripService).registerTrip(captor.capture());

        DataRegisterTrip captured = captor.getValue();

        // Assert the values are correct
        assertEquals(captured.arrivalDatetime(), arrivalDatetime);
        assertEquals(captured.departureDatetime(), departureDatetime);
        assertEquals(captured.arrivalLocalizationId(), arrivalLocalizationId);
        assertEquals(captured.departureLocalizationId(), departureLocalizationId);
        assertEquals(captured.tripValue(), tripValue);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void getAllTrips() {
        /*
        // Create a list of mocked trips
        List<Trip> mockedTrips = Arrays.asList(mockedTrip);

        // Mock the service call to return the list of mocked trips
        when(tripService.getAllTrips()).thenReturn(mockedTrips);

        ResponseEntity<List<Trip>> response = tripController.getAllTrips();

        // Verify the service method was called
        verify(tripService).getAllTrips();

        // Assert the response status
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert the response body contains the expected list of trips
        List<Trip> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(mockedTrips, responseBody);
        */
    }

    @Test
    void getAllTripsBetweenPlaces() {
    }

    @Test
    void deleteTrip() {
    }

    @Test
    void updateTrip() {
    }

    @Test
    void getCostEstimation() {
    }
}