package com.pepej.springtest.service;

import com.pepej.papi.command.Commands;
import com.pepej.papi.events.command.CommandCallEvent;
import com.pepej.springtest.job.JobPersistenceService;
import com.pepej.springtest.job.types.GameMessageJob;
import com.pepej.springtest.job.types.JobDto;
import com.pepej.springtest.job.types.PlayerSendMessageJob;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.springframework.kafka.core.KafkaTemplate;
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
                    PlayerSendMessageJob messageJob = new PlayerSendMessageJob(UUID.randomUUID(), UUID.randomUUID(), "test1");
                    persistenceService.saveJob(messageJob);
                })
                .register("test1");
        Commands.create()
                .handler(ctx -> {
                    log.info("test2!");
                    GameMessageJob messageJob = new GameMessageJob(UUID.randomUUID(), "test2");
                    persistenceService.saveJob(messageJob);
                })
                .register("test2");
    }


}
