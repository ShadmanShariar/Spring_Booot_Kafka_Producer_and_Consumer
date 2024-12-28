package com.example.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class KafkaConsumerService {

    private static final int MAX_MESSAGES = 100; // Limiting stored messages to avoid memory issues
    private final List<String> messageCache = Collections.synchronizedList(new ArrayList<>());

    @KafkaListener(topics = "my-topic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Message received: " + message);
        synchronized (messageCache) {
            if (messageCache.size() >= MAX_MESSAGES) {
                messageCache.remove(0); // Removing the oldest message
            }
            messageCache.add(message);
        }
    }

    public List<String> getRecentMessages(int count) {
        synchronized (messageCache) {
            return new ArrayList<>(messageCache.subList(Math.max(0, messageCache.size() - count), messageCache.size()));
        }
    }
}