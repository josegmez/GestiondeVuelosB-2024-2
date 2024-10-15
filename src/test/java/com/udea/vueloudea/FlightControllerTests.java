package com.udea.vueloudea;

import com.udea.vueloudea.controller.FlightController;
import com.udea.vueloudea.model.Flight;
import com.udea.vueloudea.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class FlightControllerTests {

    @MockBean
    private FlightService flightService;

    @InjectMocks
    @Autowired
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFlights() {
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        List<Flight> flights = Arrays.asList(flight1, flight2);

        when(flightService.getAllFlights()).thenReturn(flights);

        List<Flight> result = flightController.getAllFlights();
        assertEquals(2, result.size());
    }

    @Test
    void testGetFlightById() {
        Flight flight = new Flight();
        when(flightService.getFlightById(1L)).thenReturn(flight);

        Flight result = flightController.getFlightById(1L);
        assertEquals(flight, result);
    }
}