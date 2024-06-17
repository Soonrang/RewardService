package com.example.rewardservice;

import com.example.rewardservice.controller.PointLogController;
import com.example.rewardservice.dto.PointLogDTO;
import com.example.rewardservice.service.PointLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(PointLogController.class)
@AutoConfigureMockMvc
public class PointLogControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PointLogService pointLogService;

    @InjectMocks
    private PointLogController pointLogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testViewAllPointLogs() throws Exception {
        // Mock service의 동작 설정
        when(pointLogService.viewAll()).thenReturn(Collections.emptyList());

        // GET /api/points/all 요청 수행 및 검증
        mockMvc.perform(MockMvcRequestBuilders.get("/api/points/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetLatestTotalPoints() throws Exception {
        // Mock service의 동작 설정
        when(pointLogService.getLatestTotalPoints()).thenReturn(400L);

        // GET /api/points/latest-total-points 요청 수행 및 검증
        mockMvc.perform(MockMvcRequestBuilders.get("/api/points/latest-total-points"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(400L));
    }

    @Test
    public void testEarnReward() throws Exception {
        PointLogDTO pointLogDTO = new PointLogDTO();
        pointLogDTO.setEarnedPoints(100L);
        pointLogDTO.setRewardType("출석");

        // Mock service의 동작 설정
        when(pointLogService.earnReward(pointLogDTO)).thenReturn("some-id");

        // POST /api/points/earn 요청 수행 및 검증
        mockMvc.perform(MockMvcRequestBuilders.post("/api/points/earn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"earnedPoints\": 100, \"rewardType\": \"출석\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("some-id"));
    }

    @Test
    public void testUseReward() throws Exception {
        PointLogDTO pointLogDTO = new PointLogDTO();
        pointLogDTO.setUsedPoints(50L);
        pointLogDTO.setRewardType("상품 구매");

        // Mock service의 동작 설정
        when(pointLogService.useReward(pointLogDTO)).thenReturn("some-id");

        // POST /api/points/use 요청 수행 및 검증
        mockMvc.perform(MockMvcRequestBuilders.post("/api/points/use")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"usedPoints\": 50, \"rewardType\": \"상품 구매\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("some-id"));
    }
}
