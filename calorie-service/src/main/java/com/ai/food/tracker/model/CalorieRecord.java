package com.ai.food.tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "calorie_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalorieRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String messageId;
    private int calories;
    private String details;
    private Instant timestamp;
    private String username;

}
