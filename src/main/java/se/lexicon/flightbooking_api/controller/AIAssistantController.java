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
}
