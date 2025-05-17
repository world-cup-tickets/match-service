package com.worldcup.matchservice.service;

import com.worldcup.matchservice.dto.CreateMatchRequest;
import com.worldcup.matchservice.dto.MatchResponse;
import com.worldcup.matchservice.dto.dbservice.CreateMatchDto;
import com.worldcup.matchservice.dto.dbservice.MatchDto;
import com.worldcup.matchservice.repository.MatchRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    @Test
    void MatchService_CreateMatch_returnsMatchResponse() {
        CreateMatchRequest request = CreateMatchRequest.builder()
                .stadium("Camp Nou")
                .homeTeam("FC Barcelona")
                .awayTeam("Real Madrid")
                .referee("Istvan Kovacs")
                .dateTime(LocalDateTime.of(2025, 5, 16, 21, 0))
                .nrSeats(100)
                .seatPrice(50.0)
                .build();

        UUID matchId = UUID.randomUUID();

        MatchDto mockDto = new MatchDto(
                matchId,
                request.stadium(),
                request.homeTeam(),
                request.awayTeam(),
                request.referee(),
                request.dateTime(),
                request.nrSeats(),
                request.seatPrice()
        );

        when(matchRepository.createMatch(Mockito.any(CreateMatchDto.class))).thenReturn(mockDto);

        MatchResponse response = matchService.createMatch(request);

        assertNotNull(response);
        assertEquals(request.stadium(), response.stadium());
        assertEquals(request.homeTeam(), response.homeTeam());
        assertEquals(request.awayTeam(), response.awayTeam());
        assertEquals(request.referee(), response.referee());
        assertEquals(request.nrSeats(), response.nrSeats());
        assertEquals(request.seatPrice(), response.seatPrice());
    }

    @Test
    void MatchService_GetMatchById_ReturnsExpectedResponse() {
        UUID matchId = UUID.randomUUID();
        MatchDto dto = new MatchDto(
                matchId,
                "Ghencea",
                "Steaua",
                "Dinamo",
                "Hategan",
                LocalDateTime.now(),
                50,
                30.0
        );

        when(matchRepository.getMatchById(matchId)).thenReturn(dto);

        MatchResponse response = matchService.getMatchById(matchId);

        assertEquals("Ghencea", response.stadium());
        assertEquals("Steaua", response.homeTeam());
        assertEquals("Dinamo", response.awayTeam());
        assertEquals(50, response.nrSeats());
        assertEquals(30.0, response.seatPrice());
        assertEquals("Hategan", response.referee());
    }

    @Test
    void MatchService_UpdateMatch_returnsUpdatedMatchResponse() {
        UUID matchId = UUID.randomUUID();
        CreateMatchRequest request = CreateMatchRequest.builder()
                .stadium("San Siro")
                .homeTeam("Inter Milan")
                .awayTeam("AC Milan")
                .referee("Un Arbitru")
                .dateTime(LocalDateTime.of(2025, 5, 20, 18, 0))
                .nrSeats(75)
                .seatPrice(60.0)
                .build();

        MatchDto updatedDto = new MatchDto(
                matchId,
                request.stadium(),
                request.homeTeam(),
                request.awayTeam(),
                request.referee(),
                request.dateTime(),
                request.nrSeats(),
                request.seatPrice()
        );

        when(matchRepository.update(Mockito.eq(matchId), any())).thenReturn(updatedDto);

        MatchResponse result = matchService.updateMatch(matchId, request);

        assertEquals(request.stadium(), result.stadium());
        assertEquals(request.homeTeam(), result.homeTeam());
        assertEquals(request.awayTeam(), result.awayTeam());
        assertEquals(request.referee(), result.referee());
        assertEquals(request.dateTime(), result.dateTime());
        assertEquals(75, result.nrSeats());
        assertEquals(60.0, result.seatPrice());
    }

    @Test
    void MatchService_GetAllMatches_returnsListOfMatchResponses() {
        List<MatchDto> mockMatches = List.of(
                new MatchDto(UUID.randomUUID(), "Wembley", "Chelsea", "Arsenal", "Antony Taylor",
                        LocalDateTime.of(2025, 6, 1, 19, 0), 120, 55.0),
                new MatchDto(UUID.randomUUID(), "Santiago Bernabeu", "Real Madrid", "Celta Vigo", "Lahoz",
                        LocalDateTime.of(2025, 6, 2, 20, 30), 130, 90.0)
        );

        when(matchRepository.getAllMatches()).thenReturn(mockMatches);

        List<MatchResponse> results = matchService.getAllMatches();

        assertEquals(2, results.size());
        assertEquals("Wembley", results.get(0).stadium());
        assertEquals("Real Madrid", results.get(1).homeTeam());
    }

}
