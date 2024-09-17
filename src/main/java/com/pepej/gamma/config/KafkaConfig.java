package com.pepej.gamma.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.pepej.gamma.job.types.JobDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.*;

@EnableKafka
@Configuration
public class KafkaConfig {


    @Bean
    ConcurrentKafkaListenerContainerFactory<String, JobDto> kafkaJobListenerContainerFactory(ConsumerFactory<String, JobDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, JobDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(10);
        factory.getContainerProperties().setIdleEventInterval(3000L);
        return factory;
    }


    @Bean
    public NewTopic jobsTopic() {
        return TopicBuilder.name("jobs")
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    @Primary
    public ConsumerFactory<String, JobDto> consumerFactory() {
        DefaultKafkaConsumerFactory<String, JobDto> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps());
        kafkaConsumerFactory.setKeyDeserializer(new StringDeserializer());
        JsonDeserializer<JobDto> valueDeserializer = new JsonDeserializer<>(JobDto.class, objectMapper(), false);
        valueDeserializer.addTrustedPackages("*");
        kafkaConsumerFactory.setValueDeserializer(valueDeserializer);
        return kafkaConsumerFactory;
    }

    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "s");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(FIELD, ANY);
        mapper.setVisibility(GETTER, NONE);
        mapper.setVisibility(IS_GETTER, NONE);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        return mapper;
    }

    @Bean
    KafkaTemplate<String, JobDto> jobKafkaTemplate() {
        StringSerializer keySerializer = new StringSerializer();

        JsonSerializer<JobDto> valueJsonSerializer = new JsonSerializer<>(objectMapper());

        return new KafkaTemplate<>(
                new DefaultKafkaProducerFactory<>(
                        senderProps(),
                        keySerializer,
                        valueJsonSerializer));
    }

    private Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

//  x

//    @Bean
//    public KafkaTemplate<String, JobDto> jobKafkaTemplate(ProducerFactory<String, JobDto> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }
}
