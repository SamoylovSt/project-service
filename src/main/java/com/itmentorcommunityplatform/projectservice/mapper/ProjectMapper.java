package com.itmentorcommunityplatform.projectservice.mapper;

import com.itmentorcommunityplatform.projectservice.dto.ProjectResponse;
import com.itmentorcommunityplatform.projectservice.kafka.ProjectCreatedEvent;
import com.itmentorcommunityplatform.projectservice.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "authorTelegramProfileUrl", source = "telegramProfileUrl")
    @Mapping(target = "projectSourceType", constant = "FRONTEND")
    ProjectCreatedEvent toEvent(Project project, String telegramProfileUrl);

    ProjectResponse toResponse(Project project);
}
