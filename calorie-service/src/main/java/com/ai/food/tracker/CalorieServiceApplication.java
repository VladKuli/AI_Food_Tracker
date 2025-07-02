package com.ai.food.tracker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ai.food.tracker")
public class CalorieServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalorieServiceApplication.class, args);
    }
}