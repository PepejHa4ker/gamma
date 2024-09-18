package com.pepej.gamma.service;

import com.pepej.papi.command.Commands;
import com.pepej.gamma.job.JobPersistenceService;
import com.pepej.gamma.job.types.GameMessageJob;
import com.pepej.gamma.job.types.PlayerSendMessageJob;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.Bukkit;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnotherTestService {

    private final JobPersistenceService persistenceService;

    @PostConstruct
    public void init() {
        log.info("AnotherTestService init");
        Commands.create()
                .handler(ctx -> {
                    log.info("test1!");
                    PlayerSendMessageJob messageJob = new PlayerSendMessageJob(UUID.randomUUID(), UUID.randomUUID(), "test1", "hub");
                    persistenceService.saveJob(messageJob);
                })
                .register("test1");
        Commands.create()
                .handler(ctx -> {
                    log.info("test2!");
                    GameMessageJob messageJob = new GameMessageJob(UUID.randomUUID(), "test2", "hub");
                    persistenceService.saveJob(messageJob);
                })
                .register("test2");
    }


}
