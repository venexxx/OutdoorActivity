package org.example.outdooractivity.web;


import org.example.outdooractivity.model.dto.DayHoursResponse;
import org.example.outdooractivity.service.ActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<DayHoursResponse> getActivities(@RequestParam String location) {
        return activityService.getAvailableBadmintonIntervals(location);
    }
}
