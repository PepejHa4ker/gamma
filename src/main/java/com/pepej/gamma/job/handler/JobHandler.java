package com.pepej.gamma.job.handler;

import com.pepej.gamma.job.types.JobDto;
import com.pepej.gamma.job.types.JobType;

public interface JobHandler<T extends JobDto> {

    void handleJob(T jobDto);

    boolean isTypedJobValid(T jobDto);

    JobType getJobType();
}
