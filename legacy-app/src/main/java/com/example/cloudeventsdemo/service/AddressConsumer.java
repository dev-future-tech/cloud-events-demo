package com.example.cloudeventsdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class AddressConsumer {

    private final Logger log = LoggerFactory.getLogger(AddressConsumer.class);

    @Bean
    public Consumer<String> consumeAddress() {
        return (value) -> {
            log.info("Consumed value is {}", value);
        };
    }
}
