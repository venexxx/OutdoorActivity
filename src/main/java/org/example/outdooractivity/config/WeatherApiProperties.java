package org.example.outdooractivity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather.api")
public record WeatherApiProperties(
        String key,
        String baseUrl,
        int forecastDays
) {
}
