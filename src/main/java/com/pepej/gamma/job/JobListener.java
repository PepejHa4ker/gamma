package com.pepej.gamma.job;

import com.pepej.gamma.config.KafkaPersistenceProperties;
import com.pepej.gamma.job.types.JobDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobListener {

    private final static String JOB_LISTENER_ID = "job_listener";

    private final ConcurrentKafkaListenerContainerFactory<String, JobDto> kafkaJobListenerContainerFactory;
    private final JobProcessor jobProcessor;
    private final KafkaPersistenceProperties kafkaPersistenceProperties;

    @PostConstruct
    public void init() {
        log.info("Job listener started");
        log.info("Job listener configuration: {}", kafkaPersistenceProperties);
    }

    @KafkaListener(
            topics = "#{kafkaPersistenceProperties.jobsTopicName}",
            containerFactory = "kafkaJobListenerContainerFactory",
            groupId = "#{kafkaPersistenceProperties.groupId}",
            id = JOB_LISTENER_ID
    )
    public void onJobReceived(final ConsumerRecord<String, ? extends JobDto> record) {
        final JobDto jobDto = record.value();
        log.info("Received job record {}", record);
        jobProcessor.processJob(jobDto);
    }
}
