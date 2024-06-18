package com.example.rewardservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointDTO {

    private UUID id;
    private String userId;
    private int totalPoint;
    private LocalDateTime lastUpdateDate;
}