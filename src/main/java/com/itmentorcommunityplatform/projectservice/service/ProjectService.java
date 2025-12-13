package com.itmentorcommunityplatform.projectservice.service;

import com.itmentorcommunityplatform.projectservice.dto.CreateProjectViaFrontendRequest;
import com.itmentorcommunityplatform.projectservice.dto.CreateProjectViaTelegramBotOrImportRequest;
import com.itmentorcommunityplatform.projectservice.dto.ProjectResponse;
import com.itmentorcommunityplatform.projectservice.kafka.ProjectEventProducer;
import com.itmentorcommunityplatform.projectservice.mapper.ProjectCommandMapper;
import com.itmentorcommunityplatform.projectservice.mapper.ProjectMapper;
import com.itmentorcommunityplatform.projectservice.model.DataSourceType;
import com.itmentorcommunityplatform.projectservice.model.Project;
import com.itmentorcommunityplatform.projectservice.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectEventProducer projectEventProducer;
    private final ProjectMapper projectMapper;
    private final ProjectCommandMapper projectCommandMapper;



    @Transactional
    public ProjectResponse createProjectViaFrontend(Long authorId, String username, CreateProjectViaFrontendRequest request) {
        CreateProjectCommand cmd = projectCommandMapper.fromFrontend(request, authorId);
        Project savedProject = createProject(cmd);

        String telegramProfileUrl = buildTelegramProfileUrl(username);
        projectEventProducer.sendProjectCreated
                (projectMapper.toEvent(savedProject, telegramProfileUrl, DataSourceType.FRONTEND)
                );
        ProjectResponse response = projectMapper.toResponse(savedProject);
        return response;
    }

    @Transactional
    public ProjectResponse createProjectViaTelegramBotOrImporter(
            CreateProjectViaTelegramBotOrImportRequest request) {
        CreateProjectCommand cmd = projectCommandMapper.fromTelegramOrImporter(request);

        Project project = createProject(cmd);

        ProjectResponse response = projectMapper.toResponse(project);
        return response;
    }


    private Project createProject(CreateProjectCommand cmd) {

        long addedTimestamp = resolveAddedTimestamp(cmd);

        Project project = new Project(
                null,
                cmd.authorTelegramUserId(),
                cmd.githubRepositoryUrl(),
                cmd.programmingLanguage(),
                cmd.roadmapProject(),
                addedTimestamp
        );

        return projectRepository.save(project);
    }

    private long resolveAddedTimestamp(CreateProjectCommand cmd) {
        if (cmd.dataSourceType() == DataSourceType.DATA_IMPORTER
            && cmd.addedTimestamp() != null) {
            return cmd.addedTimestamp();
        }
        return Instant.now().getEpochSecond();
    }


    private String buildTelegramProfileUrl(String username) {
        return "https://t.me/" + username;
    }
}