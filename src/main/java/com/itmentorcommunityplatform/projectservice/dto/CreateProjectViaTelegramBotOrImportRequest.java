package com.itmentorcommunityplatform.projectservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.itmentorcommunityplatform.projectservice.model.DataSourceType;
import com.itmentorcommunityplatform.projectservice.model.RoadmapProject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProjectViaTelegramBotOrImportRequest(
        @NotNull
        @JsonProperty("author_telegram_user_id")
        Long authorTelegramUserId,

        @NotBlank
        @JsonProperty("github_repository_url")
        String githubRepositoryUrl,

        @NotBlank
        @JsonProperty("programming_language")
        String programmingLanguage,

        @NotNull
        @JsonProperty("roadmap_project")
        RoadmapProject roadmapProject,

        @JsonProperty("added_timestamp")
        Long addedTimestamp,

        @NotNull
        @JsonProperty("project_source_type")
        DataSourceType dataSourceType
) {
}
