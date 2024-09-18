package com.pepej.gamma.job;

import com.pepej.gamma.config.KafkaPersistenceProperties;
import com.pepej.gamma.job.types.EventSubscription;
import com.pepej.gamma.job.types.JobDto;
import com.pepej.gamma.job.types.ScheduledJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaJobPersistenceService implements JobPersistenceService {

    private final KafkaTemplate<String, JobDto> jobKafkaTemplate;
    private final KafkaPersistenceProperties kafkaPersistenceProperties;

    @Override
    public void saveJob(JobDto job) {
        try {
            jobKafkaTemplate.send(kafkaPersistenceProperties.getJobsTopicName(), job.getId().toString(), job)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.error("Error while saving job {}", job, ex);
                        }
                    });
        } catch (Exception e) {
            log.error("Error while saving job {}", job, e);
        }

    }

    @Override
    public void saveEventSubscription(EventSubscription subscription) {

    }


    @Override
    public void deleteEventSubscription(String id) {

    }

    @Override
    public void saveScheduledJob(ScheduledJob scheduledJob) {

    }

    @Override
    public void deleteScheduledJob(String id) {

    }
}
