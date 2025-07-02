package com.ai.food.tracker.controller;


import com.ai.food.tracker.model.CalorieRecord;
import com.ai.food.tracker.repository.CalorieRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/calories")
@RequiredArgsConstructor
public class CalorieController {

    private final CalorieRecordRepository repo;

    @GetMapping
    public List<CalorieRecord> getMyCalories(Authentication auth) {
        return repo.findByUsernameOrderByTimestampDesc(auth.getName());
    }
}