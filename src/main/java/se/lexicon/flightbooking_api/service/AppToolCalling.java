package se.lexicon.flightbooking_api.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;
import se.lexicon.flightbooking_api.dto.AvailableFlightDTO;
import se.lexicon.flightbooking_api.dto.BookFlightRequestDTO;
import se.lexicon.flightbooking_api.dto.FlightBookingDTO;
import se.lexicon.flightbooking_api.dto.FlightListDTO;
import se.lexicon.flightbooking_api.entity.FlightBooking;
import se.lexicon.flightbooking_api.entity.FlightStatus;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppToolCalling {
    private FlightBookingService flightBookingService;

    public AppToolCalling(FlightBookingService flightBookingService) {
        this.flightBookingService = flightBookingService;
    }

    @Tool(description = "Finds a flight by ID and updates its status to BOOKED " +
            "and updates the flights passengerName and passengerEmail with " +
            "bookingRequest passengerName and passengerEmail.")
    public FlightBookingDTO bookFlight(Long flightId, BookFlightRequestDTO bookingRequest) {
        return flightBookingService.bookFlight(flightId, bookingRequest);
    }

    @Tool(description = "Finds a flight by ID, check so the email is matches to that flight's passagerEmail and updates its status to AVAILABLE.")
    public void cancelFlight(Long flightId, String passengerEmail) {
        flightBookingService.cancelFlight(flightId, passengerEmail);
    }

//    @Tool(description = "Finds all flights with status AVAILABLE.")
//    public List<AvailableFlightDTO> findAvailableFlights() {
//        return flightBookingService.findAvailableFlights();
//    }

    @Tool(description = "Finds all bookings for a given email.")
    public List<FlightBookingDTO> findBookingsByEmail(String email) {
        return flightBookingService.findBookingsByEmail(email);
    }

//    @Tool(description = "Finds all flights.")
//    public List<FlightListDTO> findAll() {
//        return flightBookingService.findAll();
//    }
}
