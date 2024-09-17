package com.pepej.springtest;

import com.pepej.springtest.config.KafkaConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(KafkaConfig.class)
public class PapiTestSpringApplication {
}
