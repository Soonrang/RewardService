package com.example.rewardservice.controller;


import com.example.rewardservice.domain.PointLog;
import com.example.rewardservice.dto.PointLogDTO;
import com.example.rewardservice.service.PointLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/points")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PointLogController {

    private final PointLogService pointLogService;

    @PostMapping("/earn")
    public ResponseEntity<UUID> earnReward(@RequestBody PointLogDTO pointLogDTO) {
        UUID id = pointLogService.earnReward(pointLogDTO);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/use")
    public ResponseEntity<UUID> useReward(@RequestBody PointLogDTO pointLogDTO) {
        UUID id = pointLogService.useReward(pointLogDTO);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PointLog>> viewAll() {
        List<PointLog> pointLogs = pointLogService.viewAll();
        return ResponseEntity.ok(pointLogs);
    }

    @GetMapping("/latest-total-points")
    public ResponseEntity<Long> getLatestTotalPoints() {
        Long latestTotalPoints = pointLogService.getLatestTotalPoints();
        return ResponseEntity.ok(latestTotalPoints);
    }
}
