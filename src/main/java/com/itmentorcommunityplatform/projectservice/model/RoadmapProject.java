package com.itmentorcommunityplatform.projectservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum RoadmapProject {
    HANGMAN("HANGMAN"),
    SIMULATION("SIMULATION"),
    CURRENCY_EXCHANGE("CURRENCY-EXCHANGE"),
    TENNIS_SCOREBOARD("TENNIS-SCOREBOARD"),
    WEATHER_VIEWER("WEATHER-VIEWER"),
    CLOUD_FILE_STORAGE("CLOUD-FILE-STORAGE"),
    TASK_TRACKER("TASK-TRACKER"),
    OTHER("OTHER");

    private final String apiValue;

    RoadmapProject(String apiValue) {
        this.apiValue = apiValue;
    }

    @JsonValue
    public String getApiValue() {
        return apiValue;
    }

    @JsonCreator
    public static RoadmapProject fromString(String value) {
        if (value == null) {
            return null;
        }
        return Arrays.stream(RoadmapProject.values())
                .filter(p -> p.apiValue.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown roadmap project: " + value));
    }
}