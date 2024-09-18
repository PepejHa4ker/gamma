package com.pepej.gamma.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "persistence")
@Data
@Configuration
public class KafkaPersistenceProperties {
    private String bootstrapServer;
    private String groupId;
    private String jobsTopicName;
    private String scheduledJobsTopicName;

}
