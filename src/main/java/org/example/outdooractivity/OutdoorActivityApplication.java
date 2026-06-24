package org.example.outdooractivity;

import org.example.outdooractivity.config.WeatherApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(WeatherApiProperties.class)
public class OutdoorActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutdoorActivityApplication.class, args);
    }
}