package com.pepej.gamma.job;

import com.pepej.gamma.job.handler.JobHandler;
import com.pepej.gamma.job.types.JobDto;
import com.pepej.gamma.job.types.JobType;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
@Getter
public class JobProcessor {

    private static final Logger log = LoggerFactory.getLogger(JobProcessor.class);
    private final Map<JobType, JobHandler<?>> jobHandlers;

    public <T extends JobDto> void processJob(T jobDto) {
        log.info("Processing job {}", jobDto.getId());
        JobHandler<T> jobHandler = getJobHandler(jobDto);
        if (jobHandler == null) {
            log.error("No handler for type {}", jobDto.getJobType());
            return;
        }
        JobValidationResult jobValidationResult = jobHandler.validateJob(jobDto);
        if (!jobValidationResult.isSuccess()) {
            log.error("Job with id {} is not valid {}", jobDto.getId(), jobValidationResult.getErrorMessage());
            return;
        }

        jobHandler.handleJob(jobDto);
    }

    public JobProcessor(List<JobHandler<?>> jobHandlers) {
        this.jobHandlers = jobHandlers.stream().collect(toMap(JobHandler::getJobType, Function.identity()));
    }

    @SuppressWarnings("unchecked")
    public <T extends JobDto> JobHandler<T> getJobHandler(T job) {
        return (JobHandler<T>) jobHandlers.get(job.getJobType());
    }
}
