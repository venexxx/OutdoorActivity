package org.example.outdooractivity.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForecastDto(
        @JsonProperty("forecastday")
        List<ForecastDayDto> forecastday
) {
}
