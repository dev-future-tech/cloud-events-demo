package com.example.cloudeventsdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class AddressProcessor {

    private final Logger log = LoggerFactory.getLogger(AddressProcessor.class);

    @Bean
    public Function<String, String> processAddress() {
        return (value) -> {
            log.info("Processing value is {}", value);
            return value;
        };
    }

}
