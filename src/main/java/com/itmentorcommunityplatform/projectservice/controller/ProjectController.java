package com.itmentorcommunityplatform.projectservice.controller;

import com.itmentorcommunityplatform.projectservice.dto.CreateProjectViaFrontendRequest;
import com.itmentorcommunityplatform.projectservice.dto.CreateProjectViaTelegramBotOrImportRequest;
import com.itmentorcommunityplatform.projectservice.dto.ProjectResponse;
import com.itmentorcommunityplatform.projectservice.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping("/project")
    public ResponseEntity<ProjectResponse> createProjectViaFrontend(
            @RequestHeader("X-Telegram-User-Id") Long telegramUserId,
            @RequestHeader("X-Telegram-Username") String username,
            @Valid @RequestBody CreateProjectViaFrontendRequest request
    ) {
        ProjectResponse response = projectService.createProjectViaFrontend(telegramUserId, username, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/internal/project")
    public ResponseEntity<ProjectResponse> createProjectViaTelegramBotOrImporter(
            @Valid @RequestBody CreateProjectViaTelegramBotOrImportRequest request
    ) {
        ProjectResponse response = projectService.createProjectViaTelegramBotOrImporter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
