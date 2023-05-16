package com.turkcell.commonpackage.config.kafka.producer;

import com.turkcell.commonpackage.utils.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {
    private final KafkaTemplate kafkaTemplate;

    @Bean
    public KafkaProducer kafkaProducer()
    {
        return new KafkaProducer(kafkaTemplate);
    }
}
