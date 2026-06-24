package org.example.outdooractivity.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HourDto(
        @JsonProperty("time") String time,
        @JsonProperty("temp_c") double tempC,
        @JsonProperty("gust_kph") double gustKph,
        @JsonProperty("chance_of_rain") int chanceOfRain,
        @JsonProperty("cloud") int cloud
) {
}
