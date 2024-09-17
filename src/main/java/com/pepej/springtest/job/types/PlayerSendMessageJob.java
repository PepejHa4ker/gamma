package com.pepej.springtest.job.types;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class PlayerSendMessageJob extends MessageJob {

    private UUID senderId;

    public PlayerSendMessageJob(
            UUID senderId,
            UUID receiverId,
            String message
    ) {
        super("player-message", JobType.PLAYER_MESSAGE, receiverId, message);
        this.senderId = senderId;
    }
}
