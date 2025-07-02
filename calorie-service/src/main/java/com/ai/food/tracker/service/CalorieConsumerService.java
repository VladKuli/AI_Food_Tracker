package com.ai.food.tracker.service;

import com.ai.food.tracker.dto.AiResponse;
import com.ai.food.tracker.model.CalorieRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ai.food.tracker.repository.CalorieRecordRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CalorieConsumerService {

    private final CalorieRecordRepository repo;

    @KafkaListener(
            topics = "food-topic",
            groupId = "calorie-service-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(AiResponse resp) {
        CalorieRecord rec = new CalorieRecord();
        rec.setMessageId(resp.getId());
        rec.setCalories(resp.getCalories());
        rec.setDetails(resp.getDetails());
        rec.setTimestamp(Instant.now());
        rec.setUsername(resp.getUsername());
        repo.save(rec);
    }

}