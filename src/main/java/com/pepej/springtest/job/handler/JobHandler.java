package com.pepej.springtest.job.handler;

import com.pepej.springtest.job.types.JobDto;
import com.pepej.springtest.job.types.JobType;

public interface JobHandler<T extends JobDto> {

    void handleJob(T jobDto);

    boolean isTypedJobValid(T jobDto);

    JobType getJobType();
}
