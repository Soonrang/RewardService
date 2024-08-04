package com.example.rewardservice.event.application.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MonthlyAttendanceResponse {

    private final int attendanceCount;
    private final long totalPoints;
    private final boolean hasAttendance;

    public MonthlyAttendanceResponse(int attendanceCount, long totalPoints, boolean hasAttendance) {
        this.attendanceCount = attendanceCount;
        this.totalPoints = totalPoints;
        this.hasAttendance = hasAttendance;
    }

}