package com.example.rewardservice.point.domain;

import com.example.rewardservice.event.domain.Event;
import com.example.rewardservice.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PointRepository extends JpaRepository<Point, UUID> {
    List<Point> findByUserAndCreatedAtBetween(User user, LocalDateTime start, LocalDateTime end);
    List<Point> findByUserAndEventAndCreatedAtBetween(User user, Event event, LocalDateTime start, LocalDateTime end);
    List<Point> findByUserEmail(String email);
}
