package com.pepej.gamma.job.types;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public abstract class MessageJob extends JobDto {

    private UUID receiverId;
    private String message;


    public MessageJob(String jobName, JobType jobType,UUID receiverId, String message) {
        super(jobName, jobType);
        this.receiverId = receiverId;
        this.message = message;
    }
}
