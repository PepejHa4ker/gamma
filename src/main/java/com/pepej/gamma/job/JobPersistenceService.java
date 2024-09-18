package com.pepej.gamma.job;

import com.pepej.gamma.job.types.EventSubscription;
import com.pepej.gamma.job.types.JobDto;
import com.pepej.gamma.job.types.ScheduledJob;

public interface JobPersistenceService {

    void saveJob(JobDto job);

    void saveEventSubscription(EventSubscription subscription);

    void deleteEventSubscription(String id);

    void saveScheduledJob(ScheduledJob scheduledJob);

    void deleteScheduledJob(String id);
}
