package com.pepej.gamma.job.handler;

import com.pepej.gamma.job.types.JobDto;
import com.pepej.gamma.job.types.JobType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Getter
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractJobHandler<T extends JobDto> implements JobHandler<T> {

    private final JobType jobType;

    @Override
    public void handleJob(T jobDto) {
        log.debug("Handling job: {}", jobDto);
        handleTypedJob(jobDto);
    }


    abstract void handleTypedJob(T jobDto);
}
