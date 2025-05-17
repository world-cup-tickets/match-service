package com.worldcup.matchservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateMatchRequest(
        @JsonProperty("stadium") String stadium,
        @JsonProperty("home_team") String homeTeam,
        @JsonProperty("away_team") String awayTeam,
        @JsonProperty("referee") String referee,
        @JsonProperty("date_time") LocalDateTime dateTime,
        @JsonProperty("nr_seats") Integer nrSeats,
        @JsonProperty("seat_price") Double seatPrice
) {
}
