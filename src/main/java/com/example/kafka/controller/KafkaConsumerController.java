package com.example.kafka.controller;

import com.example.kafka.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaConsumerController {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @GetMapping("/consume")
    public List<String> getConsumedMessages(@RequestParam(value = "count", defaultValue = "10") int count) {
        return kafkaConsumerService.getRecentMessages(count);
    }
}