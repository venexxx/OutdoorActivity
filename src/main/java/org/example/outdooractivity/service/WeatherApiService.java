package org.example.outdooractivity.service;

import org.example.outdooractivity.model.dto.WeatherApiForecastResponse;

public interface WeatherApiService {

        WeatherApiForecastResponse getForecast(String location);
}
