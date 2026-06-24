package org.example.outdooractivity.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record ForecastDayDto(
        @JsonProperty("date") String date,
        @JsonProperty("astro") AstroDto astro,
        @JsonProperty("hour") List<HourDto> hour
) {
}
