package org.example.outdooractivity.service.impl;

import org.example.outdooractivity.model.dto.DayHoursResponse;
import org.example.outdooractivity.model.dto.ForecastDayDto;
import org.example.outdooractivity.model.dto.HourDto;
import org.example.outdooractivity.model.dto.WeatherApiForecastResponse;
import org.example.outdooractivity.service.ActivityService;
import org.example.outdooractivity.service.WeatherApiService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    private static final double MAX_GUST_KPH = 5.0;
    private static final int MAX_CHANCE_OF_RAIN = 50;
    private static final double MIN_TEMP_C = 10.0;
    private static final double MAX_TEMP_C = 30.0;
    private static final int MIN_CONSECUTIVE_HOURS = 2;

    private static final DateTimeFormatter HOUR_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final DateTimeFormatter SUN_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");

    private final WeatherApiService weatherApiService;

    public ActivityServiceImpl(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    @Override
    public List<DayHoursResponse> getAvailableBadmintonIntervals(String location) {
        WeatherApiForecastResponse response = weatherApiService.getForecast(location);

        if (response == null || response.forecast() == null || response.forecast().forecastday() == null) {
            return List.of();
        }

        List<DayHoursResponse> result = new ArrayList<>();

        for (ForecastDayDto forecastDay : response.forecast().forecastday()) {
            List<Integer> validHours = new ArrayList<>();

            LocalDate date = LocalDate.parse(forecastDay.date());
            LocalDateTime sunrise = LocalDateTime.parse(
                    forecastDay.date() + " " + forecastDay.astro().sunrise(),
                    SUN_TIME_FORMAT
            );
            LocalDateTime sunset = LocalDateTime.parse(
                    forecastDay.date() + " " + forecastDay.astro().sunset(),
                    SUN_TIME_FORMAT
            );

            for (HourDto hour : forecastDay.hour()) {
                LocalDateTime hourDateTime = LocalDateTime.parse(hour.time(), HOUR_TIME_FORMAT);

                boolean isDaylight = !hourDateTime.isBefore(sunrise) && hourDateTime.isBefore(sunset);
                boolean isTempOk = hour.tempC() >= MIN_TEMP_C && hour.tempC() <= MAX_TEMP_C;
                boolean isWindOk = hour.gustKph() < MAX_GUST_KPH;
                boolean isRainOk = hour.chanceOfRain() < MAX_CHANCE_OF_RAIN;

                if (isDaylight && isTempOk && isWindOk && isRainOk) {
                    validHours.add(hourDateTime.getHour());
                }
            }

            List<String> intervals = buildIntervals(validHours);

            if (!intervals.isEmpty()) {
                result.add(new DayHoursResponse(date.toString(), intervals));
            }
        }

        return result;
    }

    private List<String> buildIntervals(List<Integer> validHours) {
        List<String> intervals = new ArrayList<>();

        if (validHours.isEmpty()) {
            return intervals;
        }

        int start = validHours.get(0);
        int prev = validHours.get(0);

        for (int i = 1; i < validHours.size(); i++) {
            int current = validHours.get(i);

            if (current == prev + 1) {
                prev = current;
                continue;
            }

            addIntervalIfLongEnough(intervals, start, prev + 1);

            start = current;
            prev = current;
        }

        addIntervalIfLongEnough(intervals, start, prev + 1);

        return intervals;
    }

    private void addIntervalIfLongEnough(List<String> intervals, int start, int endExclusive) {
        if (endExclusive - start >= MIN_CONSECUTIVE_HOURS) {
            intervals.add(String.format("%02d-%02d", start, endExclusive));
        }
    }
}