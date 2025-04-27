package com.worldcup.matchservice.service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.worldcup.matchservice.dto.CreateMatchRequest;
import com.worldcup.matchservice.dto.MatchResponse;
import com.worldcup.matchservice.dto.dbservice.CreateMatchDto;
import com.worldcup.matchservice.dto.dbservice.MatchDto;
import com.worldcup.matchservice.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    public MatchResponse getMatchById(UUID id) {
        MatchDto match = matchRepository.getMatchById(id);

        return new MatchResponse(
                match.id(),
                match.stadium(),
                match.homeTeam(),
                match.awayTeam(),
                match.referee(),
                match.dateTime(),
                match.nrSeats(),
                match.seatPrice()
        );
    }

    public MatchResponse createMatch(CreateMatchRequest createMatchRequest) {
        CreateMatchDto createMatchDto = new CreateMatchDto(
                createMatchRequest.stadium(),
                createMatchRequest.homeTeam(),
                createMatchRequest.awayTeam(),
                createMatchRequest.referee(),
                createMatchRequest.dateTime(),
                createMatchRequest.nrSeats(),
                createMatchRequest.seatPrice()
        );

        MatchDto newMatch = matchRepository.createMatch(createMatchDto);

        return new MatchResponse(
                newMatch.id(),
                newMatch.stadium(),
                newMatch.homeTeam(),
                newMatch.awayTeam(),
                newMatch.referee(),
                newMatch.dateTime(),
                newMatch.nrSeats(),
                newMatch.seatPrice()
        );
    }

    public MatchResponse updateMatch(UUID id, CreateMatchRequest createMatchRequest) {
        CreateMatchDto createMatchDto = new CreateMatchDto(
                createMatchRequest.stadium(),
                createMatchRequest.homeTeam(),
                createMatchRequest.awayTeam(),
                createMatchRequest.referee(),
                createMatchRequest.dateTime(),
                createMatchRequest.nrSeats(),
                createMatchRequest.seatPrice()
        );

        MatchDto newMatch = matchRepository.update(id, createMatchDto);

        return new MatchResponse(
                newMatch.id(),
                newMatch.stadium(),
                newMatch.homeTeam(),
                newMatch.awayTeam(),
                newMatch.referee(),
                newMatch.dateTime(),
                newMatch.nrSeats(),
                newMatch.seatPrice()
        );
    }

    public List<MatchResponse> getAllMatches() {
        List<MatchDto> matches = matchRepository.getAllMatches();
        List<MatchResponse> response = new ArrayList<>();

        for (MatchDto match : matches) {
            response.add(new MatchResponse(
                    match.id(),
                    match.stadium(),
                    match.homeTeam(),
                    match.awayTeam(),
                    match.referee(),
                    match.dateTime(),
                    match.nrSeats(),
                    match.seatPrice()
            ));
        }
        return response;
    }
}
