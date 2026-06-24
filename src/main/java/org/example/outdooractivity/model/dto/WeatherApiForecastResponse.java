package org.example.outdooractivity.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherApiForecastResponse(
        @JsonProperty("forecast")
        ForecastDto forecast
) {
}
