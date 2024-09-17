package com.pepej.springtest.job.handler;

import com.pepej.papi.utils.Players;
import com.pepej.springtest.job.types.JobType;
import com.pepej.springtest.job.types.PlayerSendMessageJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerMessageJobHandler extends AbstractJobHandler<PlayerSendMessageJob> {

    public PlayerMessageJobHandler() {
        super(JobType.PLAYER_MESSAGE);
    }

    @Override
    void handleTypedJob(PlayerSendMessageJob jobDto) {
        log.debug("Handling player message job: {}", jobDto);
        log.info("Player {} sent message: {} to {}", jobDto.getSenderId(), jobDto.getMessage(), jobDto.getReceiverId());
    }

    @Override
    public boolean isTypedJobValid(PlayerSendMessageJob jobDto) {
        if (Players.get(jobDto.getReceiverId()).isPresent() && Players.get(jobDto.getSenderId()).isPresent()) {
            return true;
        }

        log.warn("Neither receiver or sender player not found at server!");
        return false;
    }
}
