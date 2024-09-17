package com.pepej.gamma.job.types;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GameMessageJob extends MessageJob {

    private static final UUID EMPTY_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    public GameMessageJob(UUID receiverId, String message) {
        super("game-message", JobType.GAME_MESSAGE, receiverId , message);
    }
}
