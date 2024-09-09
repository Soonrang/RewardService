package com.example.rewardservice.challenge.presentation;

import com.example.rewardservice.challenge.application.request.ChallengeCreateRequest;
import com.example.rewardservice.challenge.application.request.ChallengeUpdateRequest;
import com.example.rewardservice.challenge.application.response.ChallengeInfoResponse;
import com.example.rewardservice.challenge.application.service.ChallengeService;
import com.example.rewardservice.security.jwt.JwtTokenExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/challenge")
@RequiredArgsConstructor
public class ChallengeController {

    private final JwtTokenExtractor jwtTokenExtractor;
    private final ChallengeService challengeService;

    @PostMapping
    public ResponseEntity<UUID> createChallenge(@ModelAttribute ChallengeCreateRequest request) {
        String email = jwtTokenExtractor.getCurrentUserEmail();
        UUID challengeId = challengeService.createChallenge(email,request);
        return ResponseEntity.ok(challengeId);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateChallenge(@PathVariable UUID id, @ModelAttribute ChallengeUpdateRequest request) {
//        String email = jwtTokenExtractor.getCurrentUserEmail();
//        challengeService.updateChallenge(email, id,request);
//        return ResponseEntity.noContent().build();
//    }

    @GetMapping
    public ResponseEntity<Page<ChallengeInfoResponse>> getChallenges(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ChallengeInfoResponse> challenges = challengeService.getChallenges(page, size);
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ChallengeInfoResponse>> getChallengesByCategoryAndStatus(
            @RequestParam String category,
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ChallengeInfoResponse> challenges = challengeService.getChallengesByCategoryAndStatus(page, size, category, status);
        return ResponseEntity.ok(challenges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChallengeInfoResponse> getChallengeDetail(@PathVariable UUID id) {
        ChallengeInfoResponse challengeInfo = challengeService.viewDetail(id);

        return ResponseEntity.ok(challengeInfo);
    }



}