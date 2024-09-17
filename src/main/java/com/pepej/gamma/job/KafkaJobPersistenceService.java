package com.pepej.gamma.job;

import com.pepej.gamma.job.types.JobDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaJobPersistenceService implements JobPersistenceService{

    private final KafkaTemplate<String, JobDto> jobKafkaTemplate;

    @Override
    public void saveJob(JobDto job) {
        jobKafkaTemplate.send("jobs", job.getJobName(), job);
    }
}
