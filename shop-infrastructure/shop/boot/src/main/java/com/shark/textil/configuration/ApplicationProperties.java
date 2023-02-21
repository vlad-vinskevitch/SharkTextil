package com.shark.textil.configuration;

import com.shark.textil.domain.properties.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

    @Bean
    @ConfigurationProperties(prefix = "kafka.configuration")
    public KafkaProperties kafkaProperties() {
        return new KafkaProperties();
    }
}
