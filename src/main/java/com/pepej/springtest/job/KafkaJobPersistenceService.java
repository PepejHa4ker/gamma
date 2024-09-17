package com.pepej.springtest.job;

import com.pepej.springtest.job.types.JobDto;
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
