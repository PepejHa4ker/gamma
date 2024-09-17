package com.pepej.gamma;

import com.pepej.gamma.config.KafkaConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(KafkaConfig.class)
public class GammaSpringApplication {
}
