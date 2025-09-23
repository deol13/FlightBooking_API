package se.lexicon.flightbooking_api.service;

import se.lexicon.flightbooking_api.dto.BookFlightRequestDTO;

public interface AIAssistantService {
    String chatQuery(String query, String conversationId);
}
