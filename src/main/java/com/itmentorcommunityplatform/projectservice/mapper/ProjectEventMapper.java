package com.itmentorcommunityplatform.projectservice.mapper;

import com.itmentorcommunityplatform.projectservice.kafka.ProjectCreatedEvent;
import com.itmentorcommunityplatform.projectservice.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectEventMapper {

    ProjectCreatedEvent toEvent(Project project);
}
