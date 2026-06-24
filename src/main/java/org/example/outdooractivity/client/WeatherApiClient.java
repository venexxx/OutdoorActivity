package org.example.outdooractivity.client;

import org.example.outdooractivity.config.WeatherApiProperties;
import org.example.outdooractivity.model.dto.WeatherApiForecastResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherApiClient {

    private final RestClient restClient;
    private final WeatherApiProperties weatherApiProperties;

    public WeatherApiClient(RestClient.Builder restClientBuilder,
                            WeatherApiProperties weatherApiProperties) {
        this.restClient = restClientBuilder.build();
        this.weatherApiProperties = weatherApiProperties;

        System.out.println("KEY = " + weatherApiProperties.key());
        System.out.println("BASE URL = " + weatherApiProperties.baseUrl());
        System.out.println("DAYS = " + weatherApiProperties.forecastDays());

    }

    public WeatherApiForecastResponse getForecast(String location) {
        String url = UriComponentsBuilder
                .fromUriString(weatherApiProperties.baseUrl())
                .path("/forecast.json")
                .queryParam("key", weatherApiProperties.key())
                .queryParam("q", location)
                .queryParam("days", weatherApiProperties.forecastDays())
                .queryParam("aqi", "no")
                .queryParam("alerts", "no")
                .toUriString();

        System.out.println("Forecast URL = " + url);

        return restClient.get()
                .uri(url)
                .retrieve()
                .body(WeatherApiForecastResponse.class);
    }
}