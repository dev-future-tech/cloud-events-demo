package com.example.cloudeventsdemo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import java.util.function.Consumer;

@Component
public class AddressListener {

    @Bean
    public Consumer<Message<String>> consumerAddress() {
        return System.out::println;
    }

}
