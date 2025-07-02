package com.ai.food.tracker.service;


import com.ai.food.tracker.dto.AiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OpenAiService {

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper mapper;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public Mono<AiResponse> analyze(String description) {
        WebClient client = webClientBuilder
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();

        var body = mapper.createObjectNode()
                .put("model", "gpt-4")
                .set("messages", mapper.createArrayNode()
                        .add(mapper.createObjectNode()
                                .put("role", "system")
                                .put("content", "You are a nutrition calculator. Respond only with JSON {calories:int,details:str}."))
                        .add(mapper.createObjectNode()
                                .put("role", "user")
                                .put("content", "Calculate calories for: " + description))
                );

        return client.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(root -> {
                    String content = root
                            .path("choices").get(0)
                            .path("message").path("content")
                            .asText();
                    JsonNode result = null;
                    try {
                        result = mapper.readTree(content);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    int calories = result.path("calories").asInt();
                    String details = result.path("details").asText("");
                    return new AiResponse(null, calories, details);
                });
    }
}
