package org.example.outdooractivity.service.impl;

import org.example.outdooractivity.model.dto.DayHoursResponse;
import org.example.outdooractivity.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActivityServiceImpl implements ActivityService {
    @Override
    public List<DayHoursResponse> getAvailableBadmintonIntervals(String location) {
        return List.of();
    }
}
