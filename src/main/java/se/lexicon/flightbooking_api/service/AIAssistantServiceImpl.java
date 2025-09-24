package se.lexicon.flightbooking_api.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import se.lexicon.flightbooking_api.dto.BookFlightRequestDTO;

@Service
public class AIAssistantServiceImpl implements AIAssistantService {

    private ChatClient chatClient;
    private ChatMemory chatMemory;
    private AppToolCalling appToolCalling;

    public AIAssistantServiceImpl(ChatClient.Builder chatClient, ChatMemory chatMemory, AppToolCalling appToolCalling) {
        this.chatClient = chatClient
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                        // config the vector database
                ).build();
        this.chatMemory = chatMemory;
        this.appToolCalling = appToolCalling;
    }

    @Override
    public String chatQuery(String query, String conversationId) {

        ChatResponse chatResponse = chatClient.prompt()
                .system("""
                        You are a specialized flight booking assistant with the following capabilities:
                        1. You can fetch and display all flights using the 'findAll' tool
                        2. You can fetch and display all available flights using the 'findAvailableFlights' tool
                        3. You can search for specific flight bookings by email using the 'findBookingsByEmail' tool
                        4. You can book flights using the 'bookFlight' tool
                        5. You can cancel booked flights using the 'cancelFlight' tool
                        
                        When booking a flight, you need flight number, their name and email.
                        
                        Guidelines:
                        - Always use the appropriate tool for flight booking-related operations
                        - Only respond with flight booking-related information
                        - If a request is not about flight booking, politely explain that you can only help with flight booking
                        - When displaying flights, present them in a clear, organized manner
                        - Confirm successful operations with brief, clear messages
                        - If a request is not understood, politely explain that you can only help with flight booking-related operations
                        - If information from the user is unclear, politely explain what is unclear
                        - If you need more information from the user, politely explain that you need the missing information
                        """)
                .user(query)
                .tools(appToolCalling)
                .options(OpenAiChatOptions.builder().temperature(0.2).maxTokens(500).build())
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .chatResponse();

        return chatResponse.getResult().getOutput().getText();
    }
}
