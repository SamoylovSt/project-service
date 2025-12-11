package com.itmentorcommunityplatform.projectservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("projects")
public record Project(
        @Id
        Long id,
        Long authorTelegramUserId,
        String githubRepositoryUrl,
        String programmingLanguage,
        RoadmapProject roadmapProject,
        Long addedTimestamp
) {
}