package com.shark.textil.domain.properties;

import lombok.Data;

@Data
public class KafkaProperties {

    private String bootstrapServer;
    private Producer producer;
    private Consumer consumer;

    @Data
    public static class Producer {

        private String retries;
        private String batchSize;
        private String linerMs;
        private String bufferMemory;
    }

    @Data
    public static class Consumer {
        private String groupId;
    }
}
