package com.shark.textil.configuration.properties;

import com.shark.textil.domain.props.JwtProperties;
import com.shark.textil.domain.props.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

    @Bean
    @ConfigurationProperties(prefix = "security.jwt.token")
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "kafka.configuration")
    public KafkaProperties kafkaProperties() {
        return new KafkaProperties();
    }
}
