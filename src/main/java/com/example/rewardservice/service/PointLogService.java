package com.example.rewardservice.service;

import com.example.rewardservice.domain.PointLog;
import com.example.rewardservice.dto.PointLogDTO;
import com.example.rewardservice.repository.PointLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PointLogService {

    private final PointLogRepository pointLogRepository;

    public String earnReward(PointLogDTO pointLogDTO) {
        PointLog pointLog = new PointLog();
        pointLog.setEarnedPoints(pointLogDTO.getEarnedPoints());
        pointLog.setRewardType(pointLogDTO.getRewardType());
        pointLog.setCreatedAt(LocalDateTime.now());

        long previousTotalPoints = getLastTotalPoints();
        long totalPoints = previousTotalPoints + pointLog.getEarnedPoints();
        pointLog.setTotalPoints(totalPoints);

        PointLog savedPointLog = pointLogRepository.save(pointLog);
        return savedPointLog.getId();
    }

    public String useReward(PointLogDTO pointLogDTO) {
        PointLog pointLog = new PointLog();
        pointLog.setUsedPoints(pointLogDTO.getUsedPoints());
        pointLog.setRewardType(pointLogDTO.getRewardType());
        pointLog.setCreatedAt(LocalDateTime.now());

        long previousTotalPoints = getLastTotalPoints();
        long totalPoints = previousTotalPoints - pointLog.getUsedPoints();
        pointLog.setTotalPoints(totalPoints);

        PointLog savedPointLog = pointLogRepository.save(pointLog);
        return savedPointLog.getId();
    }


    public List<PointLog> viewAll(){
        return this.pointLogRepository.findAll();
    }

    public Long getLatestTotalPoints() {
        PointLog latestPointLog = pointLogRepository.findTopByOrderByCreatedAtDesc();
        return latestPointLog != null ? latestPointLog.getTotalPoints() : 0L;
    }

    private long getLastTotalPoints(){
        PointLog lastPointLog = pointLogRepository.findTopByOrderByCreatedAtDesc();
        return lastPointLog != null ? lastPointLog.getTotalPoints() : 0;
    }


}
