package com.itmentorcommunityplatform.projectservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.projects-project-created}")
    private String projectsProjectCreatedTopic;

    public void sendProjectCreated(ProjectCreatedEvent event) {
        kafkaTemplate.send(projectsProjectCreatedTopic, event);
    }
}
