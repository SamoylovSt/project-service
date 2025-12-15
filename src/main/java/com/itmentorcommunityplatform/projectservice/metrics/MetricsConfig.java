package com.itmentorcommunityplatform.projectservice.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public Counter projectsCreatedViaFrontendCounter(MeterRegistry registry) {
        return Counter.builder("projects_created_frontend_total")
                .description("Total projects created via frontend")
                .register(registry);
    }

    @Bean
    public Counter projectsCreatedViaTelegramBotCounter(MeterRegistry registry) {
        return Counter.builder("projects_created_telegram_bot_total")
                .description("Total projects created via telegram bot")
                .register(registry);
    }

    @Bean
    public Counter projectsCreatedViaImporterCounter(MeterRegistry registry) {
        return Counter.builder("projects_created_data_importer_total")
                .description("Total projects created via data importer")
                .register(registry);
    }

}
