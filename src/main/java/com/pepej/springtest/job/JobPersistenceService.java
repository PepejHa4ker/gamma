package com.pepej.springtest.job;

import com.pepej.springtest.job.types.JobDto;

public interface JobPersistenceService {


    void saveJob(JobDto job);
}
