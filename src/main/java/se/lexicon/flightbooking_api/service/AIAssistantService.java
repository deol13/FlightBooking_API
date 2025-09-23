package se.lexicon.flightbooking_api.service;

import se.lexicon.flightbooking_api.dto.BookFlightRequestDTO;

public interface AIAssistantService {
    String chatQuery(String query, String conversationId);

    String chatBookFlight(String query, String conversationId, Long flightId, BookFlightRequestDTO bookingRequest);

    String chatGetBookingsByEmail(String query, String conversationId, String email);

    String chatCancelFlightBooking(String query, String conversationId, Long flightId, String email);
}
