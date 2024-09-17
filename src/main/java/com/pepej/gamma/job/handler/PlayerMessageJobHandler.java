package com.pepej.gamma.job.handler;

import com.pepej.gamma.job.JobValidationResult;
import com.pepej.papi.utils.Players;
import com.pepej.gamma.job.types.JobType;
import com.pepej.gamma.job.types.PlayerSendMessageJob;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.entity.Player;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerMessageJobHandler implements JobHandler<PlayerSendMessageJob> {

    @Override
    public void handleJob(PlayerSendMessageJob jobDto) {
        log.debug("Handling player message job: {}", jobDto);
        log.info("Player {} sent message: {} to {}", jobDto.getSenderId(), jobDto.getMessage(), jobDto.getReceiverId());
    }

    @Override
    public JobValidationResult validateJob(PlayerSendMessageJob jobDto) {
        Player sender = Players.getNullable(jobDto.getSenderId());
        if (sender == null) {
            return JobValidationResult.error("Sender with id " + jobDto.getSenderId() + " not found");
        }
        Player receiver = Players.getNullable(jobDto.getReceiverId());
        if (receiver == null) {
            return JobValidationResult.error("Receiver with id " + jobDto.getReceiverId() + " not found");
        }
        if (sender.equals(receiver)) {
            return JobValidationResult.error("Sender and Receiver are the same");
        }
        return JobValidationResult.ok();
    }

    @Override
    public JobType getJobType() {
        return JobType.PLAYER_MESSAGE;
    }
}
