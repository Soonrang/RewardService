package com.example.rewardservice.event.application.dto.response;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class EventResponse {
    private String id;
    private String name;
    private String description;
    private String eventType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}