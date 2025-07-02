package com.ai.food.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiResponse {

    private String id;
    private int calories;
    private String details;
    private String username;

    public AiResponse(String id, int calories, String details) {
        this.id = id;
        this.calories = calories;
        this.details = details;
        this.username = null;
    }
}
