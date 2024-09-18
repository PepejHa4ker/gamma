package com.pepej.gamma;

import com.pepej.gamma.config.KafkaConfig;
import com.pepej.gamma.config.KafkaPersistenceProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        KafkaConfig.class,
})
@EnableConfigurationProperties(KafkaPersistenceProperties.class)
public class GammaSpringApplication {
}
