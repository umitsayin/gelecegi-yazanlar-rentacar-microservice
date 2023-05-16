package com.turkcell.commonpackage.utils.kafka.producer;

import com.turkcell.commonpackage.event.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@RequiredArgsConstructor
public class KafkaProducer{
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public <T extends Event> void sendMessage(String topic, T event){
        log.info(String.format("Event => %s",event.toString()));
        Message<T> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,topic)
                .build();

        kafkaTemplate.send(message);
    }
}
