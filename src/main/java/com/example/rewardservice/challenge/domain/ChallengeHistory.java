package com.example.rewardservice.challenge.domain;

import com.example.rewardservice.common.BaseEntity;
import com.example.rewardservice.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ChallengeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    private Challenge challenge;

    @ManyToOne
    private UserChallenge userChallenge;

    private String status;

    private String reason;

    private LocalDateTime createAt;

    private long point;

    public ChallengeHistory(User user, Challenge challenge, String status, String reason, long point) {
        this.user = user;
        this.challenge = challenge;
        this.status = status;
        this.reason = reason;
        this.createAt = LocalDateTime.now();
        this.point = point;
    }



}
