package org.example.outdooractivity.model.dto;

import java.util.List;

public record DayHoursResponse(

        String date,
        List<String> hours
) {
}
