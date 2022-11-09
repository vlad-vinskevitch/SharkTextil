package com.shark.textil.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

//    @KafkaListener(topics = "some_topic", groupId = "some-group")
    public void notificationReceiver(byte[] request) {

    }
}
