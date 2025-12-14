package com.itmentorcommunityplatform.projectservice.service;

import com.itmentorcommunityplatform.projectservice.dto.CreateProjectViaFrontendRequest;
import com.itmentorcommunityplatform.projectservice.dto.CreateProjectViaTelegramBotOrImportRequest;
import com.itmentorcommunityplatform.projectservice.dto.ProjectResponse;
import com.itmentorcommunityplatform.projectservice.kafka.ProjectEventProducer;
import com.itmentorcommunityplatform.projectservice.mapper.ProjectMapper;
import com.itmentorcommunityplatform.projectservice.model.DataSourceType;
import com.itmentorcommunityplatform.projectservice.model.Project;
import com.itmentorcommunityplatform.projectservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectEventProducer projectEventProducer;
    private final ProjectMapper projectMapper;


    @Transactional
    public ProjectResponse createProjectViaFrontend(
            Long authorId,
            String username,
            CreateProjectViaFrontendRequest request
    ) {
        long timestamp = Instant.now().getEpochSecond();

        log.info("Creating project via FRONTEND. authorId={}, username={}, timestamp={}",
                authorId, username, timestamp);

        Project project = projectMapper.toEntity(request, authorId, timestamp);
        projectRepository.save(project);

        log.info("Project created via FRONTEND. projectId={}", project.getId());

        String telegramProfileUrl = buildTelegramProfileUrl(username);
        projectEventProducer.sendProjectCreated(
                projectMapper.toEvent(project, telegramProfileUrl, DataSourceType.FRONTEND)
        );

        return projectMapper.toResponse(project);
    }

    @Transactional
    public ProjectResponse createProjectViaTelegramBotOrImporter(
            CreateProjectViaTelegramBotOrImportRequest request
    ) {
        long addedTimestamp = request.dataSourceType() == DataSourceType.DATA_IMPORTER
                ? request.addedTimestamp()
                : Instant.now().getEpochSecond();

        Project project = projectMapper.toEntity(request, addedTimestamp);
        projectRepository.save(project);
        log.info("Project saved via {}. projectId={}",
                request.dataSourceType(), project.getId());

        String telegramProfileUrl = buildTelegramProfileUrl(request.telegramUsername());
        projectEventProducer.sendProjectCreated(
                projectMapper.toEvent(project, telegramProfileUrl, request.dataSourceType())
        );
        return projectMapper.toResponse(project);
    }

    private String buildTelegramProfileUrl(String username) {
        return "https://t.me/" + username;
    }
}
