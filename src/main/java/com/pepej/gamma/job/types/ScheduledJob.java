package com.pepej.gamma.job.types;

import lombok.Value;

import java.time.Instant;

public class ScheduledJob extends JobDto {

    SchedulerPayload payload;
    Instant dueDate;
    String repeatExpression;
    ScheduledActionType actionType;

    sealed interface SchedulerPayload {}

    final static class EmptyPayload implements SchedulerPayload {}

    @Value
    static class JobPayload implements SchedulerPayload {

        JobDto jobDto;
    }


}
