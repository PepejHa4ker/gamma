package com.pepej.gamma.job.handler;

import com.pepej.papi.utils.Players;
import com.pepej.gamma.job.types.GameMessageJob;
import com.pepej.gamma.job.types.JobType;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.entity.Player;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GameMessageJobHandler extends AbstractJobHandler<GameMessageJob> {

    public GameMessageJobHandler() {
        super(JobType.GAME_MESSAGE);
    }

    @Override
    void handleTypedJob(GameMessageJob jobDto) {
        Player receiver = Players.getNullable(jobDto.getReceiverId());

        if (receiver == null) {
            return;
        }

        log.debug("{} received game message {}", receiver.getName(), jobDto.getMessage());

    }

    @Override
    public boolean isTypedJobValid(GameMessageJob jobDto) {
        return Players.get(jobDto.getReceiverId()).isPresent();
    }
}
