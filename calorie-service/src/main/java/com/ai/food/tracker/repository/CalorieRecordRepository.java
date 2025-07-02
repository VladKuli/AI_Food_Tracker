package com.ai.food.tracker.repository;

import com.ai.food.tracker.model.CalorieRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalorieRecordRepository extends JpaRepository<CalorieRecord, Long> {

    List<CalorieRecord> findByUsernameOrderByTimestampDesc(String username);
}
