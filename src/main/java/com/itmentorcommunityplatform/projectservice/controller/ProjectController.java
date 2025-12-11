package com.itmentorcommunityplatform.projectservice.controller;

import com.itmentorcommunityplatform.projectservice.dto.CreateProjectRequest;
import com.itmentorcommunityplatform.projectservice.dto.ProjectResponse;
import com.itmentorcommunityplatform.projectservice.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project")
    public ResponseEntity<ProjectResponse> createProject(
            @RequestHeader("X-Telegram-User-Id") Long telegramUserId,
            @Valid @RequestBody CreateProjectRequest request
    ) {
        ProjectResponse response = projectService.createProject(telegramUserId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
