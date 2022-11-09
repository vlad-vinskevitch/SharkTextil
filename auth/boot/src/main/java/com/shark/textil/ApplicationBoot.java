package com.shark.textil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("ua.shark.textil.configuration.properties")
public class ApplicationBoot {
    public static void main(final String[] args) {
        SpringApplication.run(ApplicationBoot.class);
    }
}
