package se.lexicon.flightbooking_api.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.lexicon.flightbooking_api.dto.BookFlightRequestDTO;
import se.lexicon.flightbooking_api.service.AIAssistantService;

@RestController
@RequestMapping("/api/chatBot")
@RequiredArgsConstructor
@Tag(name = "Flight Booking AI Assistant API", description = "APIs for flight booking AI assistant operations")
public class AIAssistantController {

    final private AIAssistantService service;

    @GetMapping("/explain")
    public String explain() {
        return "Hello from AI Assistant: explain";
    }

    /// Ask the AI assistant a question.
    @GetMapping("/ask")
    public String ask(@RequestParam
            @NotNull
            @NotBlank
            String question,
            @NotNull
            @NotBlank
            String conversationId) {
        System.out.println("question = " + question);
        System.out.println("conversationId = " + conversationId);
        return service.chatQuery(question, conversationId);
    }

    /// AI assistant books for user.
    @PostMapping("/{flightId}/bookFlight")
    public String askBookFlight(
            @RequestParam
            @NotNull
            @NotBlank
            String question,
            @NotNull
            @NotBlank
            String conversationId,
            @Parameter(description = "ID of the flight to book") @PathVariable Long flightId,
            @Parameter(description = "Booking request details") @RequestBody BookFlightRequestDTO bookingRequest){
        return "Hello from AI Assistant: askBookFlight";
    }

    ///  AI Assistant get user bookings
    @GetMapping("/BookingsByEmail")
    public String askGetBookingsByEmail(
            @RequestParam
            @NotNull
            @NotBlank
            String question,
            @NotNull
            @NotBlank
            String conversationId,
            @Parameter(description = "Email to search bookings for") @RequestParam String email){
        return "Hello from AI Assistant: askGetBookingsByEmail";
    }

    ///  AI assistant cancels user's booking
    @DeleteMapping("/{flightId}/cancelBooking")
    public String askCancelFlightBooking(
            @RequestParam
            @NotNull
            @NotBlank
            String question,
            @NotNull
            @NotBlank
            String conversationId,
            @Parameter(description = "ID of the flight to cancel") @PathVariable Long flightId,
            @Parameter(description = "Email associated with the booking") @RequestParam String email){
        return "Hello from AI Assistant: askCancelFlightBooking";
    }
}
