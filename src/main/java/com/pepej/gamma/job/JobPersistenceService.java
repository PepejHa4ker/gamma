package com.pepej.gamma.job;

import com.pepej.gamma.job.types.JobDto;

public interface JobPersistenceService {


    void saveJob(JobDto job);
}
