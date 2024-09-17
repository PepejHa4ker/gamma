package com.pepej.gamma.job.types;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlayerSendMessageJob.class),
        @JsonSubTypes.Type(value = GameMessageJob.class),
})
public class JobDto {
    private UUID id;
    private String jobName;
    private JobType jobType;

    public JobDto(String jobName, JobType jobType) {
        this.id = UUID.randomUUID();
        this.jobName = jobName;
        this.jobType = jobType;
    }

}
