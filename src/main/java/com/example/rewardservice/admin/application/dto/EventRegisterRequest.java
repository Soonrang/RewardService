package com.example.rewardservice.admin.application.dto;

import com.example.rewardservice.event.domain.EventType;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class EventRegisterRequest {
    private String name;
    private String eventState;
    private EventType eventType;
    private LocalDate startDate;
    private LocalDate endDate;
}
