package com.pepej.gamma.service;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import com.pepej.gamma.GammaSpringPlugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnotherTestServiceTest {

    ServerMock server;
    GammaSpringPlugin plugin;
    PlayerMock player;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(GammaSpringPlugin.class);
        player = server.addPlayer();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test() {
        player.performCommand("test1");
    }
}