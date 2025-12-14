package com.itmentorcommunityplatform.projectservice.kafka;

import com.itmentorcommunityplatform.projectservice.model.DataSourceType;

public record ProjectCreatedEvent(Long authorTelegramUserId,
                                  String authorTelegramProfileUrl,
                                  String githubRepositoryUrl,
                                  String programmingLanguage,
                                  String roadmapProject,
                                  Long addedTimestamp,
                                  DataSourceType projectSourceType) {
}
