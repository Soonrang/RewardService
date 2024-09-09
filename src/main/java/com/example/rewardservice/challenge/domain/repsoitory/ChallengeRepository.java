package com.example.rewardservice.challenge.domain.repsoitory;

import com.example.rewardservice.challenge.domain.Challenge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ChallengeRepository extends JpaRepository<Challenge, UUID> {
    Page<Challenge> findByCategory(String category, Pageable pageable);
    //Page<Challenge> findByCategoryAndState(String category, String state, Pageable pageable);


    @Query("SELECT c FROM Challenge c " +
            "WHERE c.category = :category " +
            "AND (:state = 'PENDING' AND c.challengePeriod.startDate > CURRENT_DATE) " +
            "OR (:state = 'ONGOING' AND c.challengePeriod.startDate <= CURRENT_DATE AND c.challengePeriod.endDate >= CURRENT_DATE) " +
            "OR (:state = 'COMPLETED' AND c.challengePeriod.endDate < CURRENT_DATE)")
    Page<Challenge> findByCategoryAndState(@Param("category") String category,
                                            @Param("state") String state,
                                            Pageable pageable);
}
