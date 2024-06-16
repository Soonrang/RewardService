package com.example.rewardservice.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointLogDTO {
    private UUID id;
    private Long earnedPoints;
    private String rewardType;
    private LocalDateTime createdAt;
    private Long usedPoints;
    private LocalDateTime useTime;

}
