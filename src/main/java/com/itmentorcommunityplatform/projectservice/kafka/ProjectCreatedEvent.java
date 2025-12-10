package com.itmentorcommunityplatform.projectservice.kafka;

public record ProjectCreatedEvent(Long authorTelegramUserId,
                                  String authorTelegramProfileUrl,
                                  String githubRepositoryUrl,
                                  String programmingLanguage,
                                  String roadmapProject,
                                  Long addedTimestamp,
                                  String projectSourceType) {
}
