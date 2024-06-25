package com.example.rewardservice.repository;

import com.example.rewardservice.domain.Event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
