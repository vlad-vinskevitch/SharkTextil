package com.shark.tesxtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.shark.textil.configuration")
public class ShopApplication {
    public static void main(final String[] args) {
        SpringApplication.run(ShopApplication.class);
    }
}
