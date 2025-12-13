package com.itmentorcommunityplatform.projectservice.mapper;

import com.itmentorcommunityplatform.projectservice.dto.CreateProjectViaFrontendRequest;
import com.itmentorcommunityplatform.projectservice.dto.CreateProjectViaTelegramBotOrImportRequest;
import com.itmentorcommunityplatform.projectservice.service.CreateProjectCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectCommandMapper {

    @Mapping(target = "authorTelegramUserId", source = "authorId")
    @Mapping(target = "dataSourceType", constant = "FRONTEND")
    @Mapping(target = "addedTimestamp", ignore = true)
    CreateProjectCommand fromFrontend(
            CreateProjectViaFrontendRequest request,
            Long authorId
    );

    CreateProjectCommand fromTelegramOrImporter(
            CreateProjectViaTelegramBotOrImportRequest request
    );
}
