package com.itmentorcommunityplatform.projectservice.service;

import com.itmentorcommunityplatform.projectservice.dto.CreateProjectRequest;
import com.itmentorcommunityplatform.projectservice.dto.ProjectResponse;
import com.itmentorcommunityplatform.projectservice.model.Project;
import com.itmentorcommunityplatform.projectservice.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

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