package org.example.outdooractivity.service;

import org.example.outdooractivity.model.dto.DayHoursResponse;

import java.util.List;

public interface ActivityService {

    List<DayHoursResponse> getAvailableBadmintonIntervals(String location);
}
