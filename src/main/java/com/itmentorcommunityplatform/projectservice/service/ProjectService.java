package com.itmentorcommunityplatform.projectservice.service;

import com.itmentorcommunityplatform.projectservice.dto.CreateProjectRequest;
import com.itmentorcommunityplatform.projectservice.dto.ProjectResponse;
import com.itmentorcommunityplatform.projectservice.kafka.ProjectEventProducer;
import com.itmentorcommunityplatform.projectservice.mapper.ProjectEventMapper;
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
    private final ProjectEventMapper projectEventMapper;


    @Transactional
    public ProjectResponse createProject(Long authorId, CreateProjectRequest request) {
        Project project = new Project(
                null,
                authorId,
                request.githubRepositoryUrl(),
                request.programmingLanguage(),
                request.roadmapProject(),
                Instant.now().getEpochSecond()
        );
        Project savedProject = projectRepository.save(project);
        projectEventProducer.sendProjectCreated(projectEventMapper.toEvent(savedProject));
        return new ProjectResponse(
                savedProject.id(),
                savedProject.authorTelegramUserId(),
                savedProject.githubRepositoryUrl(),
                savedProject.programmingLanguage(),
                savedProject.roadmapProject(),
                savedProject.addedTimestamp()
        );
    }
}