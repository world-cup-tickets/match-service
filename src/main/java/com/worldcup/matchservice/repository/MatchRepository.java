package com.worldcup.matchservice.repository;

import com.worldcup.matchservice.dto.dbservice.CreateMatchDto;
import com.worldcup.matchservice.dto.dbservice.MatchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;
import java.util.UUID;

@HttpExchange(url = "/api/v1/match")
public interface MatchRepository {
    @GetExchange("/{id}/get")
    MatchDto getMatchById(@PathVariable UUID id);

    @PostExchange("/create")
    MatchDto createMatch(@RequestBody CreateMatchDto match);

    @GetExchange("/getAllMatches")
    List<MatchDto> getAllMatches();

    @PutExchange("/{id}/update")
    MatchDto update(@PathVariable UUID id, @RequestBody CreateMatchDto dto);
}
