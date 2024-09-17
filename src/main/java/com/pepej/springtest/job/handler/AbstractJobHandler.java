package com.pepej.springtest.job.handler;

import com.pepej.springtest.job.types.JobDto;
import com.pepej.springtest.job.types.JobType;
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
