package com.pepej.gamma.job.handler;

import com.pepej.gamma.job.JobValidationResult;
import com.pepej.papi.utils.Players;
import com.pepej.gamma.job.types.GameMessageJob;
import com.pepej.gamma.job.types.JobType;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.entity.Player;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GameMessageJobHandler implements JobHandler<GameMessageJob> {

    @Override
    public void handleJob(GameMessageJob jobDto) {
        Player receiver = Players.getNullable(jobDto.getReceiverId());
        if (receiver == null) {
            return;
        }
        log.debug("{} received game message {}", receiver.getName(), jobDto.getMessage());
    }

    @Override
    public JobValidationResult validateJob(GameMessageJob jobDto) {
        if (Players.get(jobDto.getReceiverId()).isEmpty()) {
            return JobValidationResult.error("Player with id " + jobDto.getReceiverId() + " not found");
        }
        return JobValidationResult.ok();
    }

    @Override
    public JobType getJobType() {
        return JobType.GAME_MESSAGE;
    }
}
