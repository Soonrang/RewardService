package com.example.rewardservice.repository;

import com.example.rewardservice.domain.PointLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointLogRepository extends JpaRepository<PointLog, Long> {
    PointLog findTopByOrderByCreatedAtDesc();
}
