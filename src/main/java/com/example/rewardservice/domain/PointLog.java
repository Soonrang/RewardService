package com.example.rewardservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointLog {

    @Id
    @Column(name = "log_id", updatable = false, nullable = false)
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private long earnedPoints;

    @Column(nullable = false)
    private String rewardType;

    @Column(nullable = false)
    private long usedPoints;

    @Column(nullable = false)
    private long totalPoints;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public void earnPoints(long points, String rewardType) {
        this.earnedPoints = points;
        this.totalPoints += points;
        this.rewardType = rewardType;
        this.createdAt = LocalDateTime.now();
    }

    public void usePoints(long points) {
        this.usedPoints = points;
        this.totalPoints -= points;
        this.createdAt = LocalDateTime.now();
    }

}
