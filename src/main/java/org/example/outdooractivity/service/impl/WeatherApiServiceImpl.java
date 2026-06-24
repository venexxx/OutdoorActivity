package org.example.outdooractivity.service.impl;

import org.example.outdooractivity.client.WeatherApiClient;
import org.example.outdooractivity.model.dto.WeatherApiForecastResponse;
import org.example.outdooractivity.service.WeatherApiService;
import org.springframework.stereotype.Service;

@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    private final WeatherApiClient weatherApiClient;

    public WeatherApiServiceImpl(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    @Override
    public WeatherApiForecastResponse getForecast(String location) {
        return weatherApiClient.getForecast(location);
    }

}
