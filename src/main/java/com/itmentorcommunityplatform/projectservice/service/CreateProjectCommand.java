package com.itmentorcommunityplatform.projectservice.service;

import com.itmentorcommunityplatform.projectservice.model.DataSourceType;
import com.itmentorcommunityplatform.projectservice.model.RoadmapProject;

public record CreateProjectCommand(
        Long authorTelegramUserId,
        String githubRepositoryUrl,
        String programmingLanguage,
        RoadmapProject roadmapProject,
        DataSourceType dataSourceType,
        Long addedTimestamp
) {
}
