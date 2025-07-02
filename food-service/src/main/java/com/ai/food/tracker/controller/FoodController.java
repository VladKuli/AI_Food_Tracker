package com.ai.food.tracker.controller;


import com.ai.food.tracker.dto.AiResponse;
import com.ai.food.tracker.dto.FoodRequest;
import com.ai.food.tracker.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/food")
@RequiredArgsConstructor
public class FoodController {

    private final OpenAiService aiService;
    private final KafkaTemplate<String, Object> kafka;

    @PostMapping("/analyze")
    public Mono<AiResponse> analyze(@RequestBody FoodRequest req, Authentication auth) {
        return aiService.analyze(req.getDescription())
                .map(resp -> {
                    resp.setId(UUID.randomUUID().toString());
                    resp.setUsername(auth.getName());
                    kafka.send("food-topic", resp.getId(), resp);
                    return resp;
                });
    }
}
