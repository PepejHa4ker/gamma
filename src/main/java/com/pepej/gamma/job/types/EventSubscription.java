package com.pepej.gamma.job.types;

import lombok.Data;

import java.util.UUID;

@Data
public class EventSubscription {
    private String serverId;
    private UUID subscriptionId;
    private EventType eventType;
    private String eventName;

}


enum EventType {
    TARGET_PLAYER_JOIN,
    TARGET_PLAYER_QUIT,
    GAME_START,
    GAME_END,
    MESSAGE,
    ACTION,
}
