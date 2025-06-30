package com.ai.food.tracker.config;

import com.ai.food.tracker.dto.AiResponse;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, AiResponse> aiResponseConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "calorie-service-group");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        JsonDeserializer<AiResponse> deserializer = new JsonDeserializer<>(AiResponse.class);
        deserializer.addTrustedPackages("com.ai.food.tracker.calorieservice.dto");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AiResponse> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AiResponse> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(aiResponseConsumerFactory());
        return factory;
    }
}
