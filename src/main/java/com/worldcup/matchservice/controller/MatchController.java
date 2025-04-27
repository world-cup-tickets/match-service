package com.worldcup.matchservice.controller;

import com.worldcup.matchservice.dto.CreateMatchRequest;
import com.worldcup.matchservice.dto.MatchResponse;
import com.worldcup.matchservice.dto.dbservice.CreateMatchDto;
import com.worldcup.matchservice.dto.dbservice.MatchDto;
import com.worldcup.matchservice.repository.MatchRepository;
import com.worldcup.matchservice.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/match")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("{id}")
    public MatchResponse getMatchById(@PathVariable UUID id) {
        return matchService.getMatchById(id);
    }

    @PostMapping("/create")
    public MatchResponse createMatch(@RequestBody CreateMatchRequest match) {
        return matchService.createMatch(match);
    }

    @PutMapping("{id}/update")
    public MatchResponse updateMatch(@PathVariable UUID id, @RequestBody CreateMatchRequest match) {
        return matchService.updateMatch(id, match);
    }

    @GetMapping("getAllMatches")
    public List<MatchResponse> getAllMatches() {
        return matchService.getAllMatches();
    }
}
